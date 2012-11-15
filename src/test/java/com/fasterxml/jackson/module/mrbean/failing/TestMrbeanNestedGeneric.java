package com.fasterxml.jackson.module.mrbean.failing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.mrbean.BaseTest;
import com.fasterxml.jackson.module.mrbean.MrBeanModule;

import java.util.List;

/**
 * Tests to verify whether generic declarations are properly handled by Mr Bean.
 * Currently (1.8) this is not the case, and fix is non-trivial; not impossible,
 * just quite difficult.
 */
public class TestMrbeanNestedGeneric extends BaseTest
{
    // For [JACKSON-479]
    public interface ResultWrapper<T> {
        T getValue();
    }
    
    public interface Results<T> {
        Long getTotal();
        List<T> getRecords();
    }
    public interface Dog {
        String getName();
        String getBreed();
    }

    /*
    /**********************************************************
    /* Unit tests
    /**********************************************************
     */
    
    // For [JACKSON-479]
    public void testTypeReferenceNestedGeneric() throws Exception
    {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new MrBeanModule());
        final String JSON = "{\"value\":{\"breed\":\"Poodle\",\"name\":\"Rekku\"}}";

        final ResultWrapper<Dog> result = mapper.readValue(JSON, 
                new TypeReference<ResultWrapper<Dog>>() { });
        Object ob = result.getValue();
        assertTrue(Dog.class.isAssignableFrom(ob.getClass()));
    }

    // For [JACKSON-479]
    public void testTypeReferenceNestedGenericList() throws Exception
    {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new MrBeanModule());

        final String JSON = "{\"records\":[{\"breed\":\"Mountain Cur\",\"name\":\"Fido\"}],\n"
            +"\"total\":1}";
        
        final Results<Dog> result = mapper.readValue(JSON, new TypeReference<Results<Dog>>() { });

        List<?> records = result.getRecords();
        assertEquals(1, records.size());
        assertTrue(Dog.class.isAssignableFrom(records.get(0).getClass()));
    }

}
