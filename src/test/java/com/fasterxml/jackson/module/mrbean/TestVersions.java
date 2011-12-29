package com.fasterxml.jackson.module.mrbean;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;

/**
 * Tests to verify [JACKSON-278]
 */
public class TestVersions extends BaseTest
{
    /**
     * Not a good to do this, but has to do, for now...
     */
    private final static int MAJOR_VERSION = 2;
    private final static int MINOR_VERSION = 0;

    // could inject using Maven filters as well...
    private final static String GROUP_ID = "com.fasterxml.jackson";
    private final static String ARTIFACT_ID = "jackson-module-mrbean";
    
    public void testMapperVersions()
    {
        assertVersion(new AbstractTypeMaterializer());
        assertVersion(new MrBeanModule());
    }

    /*
    /**********************************************************
    /* Helper methods
    /**********************************************************
     */
    
    private void assertVersion(Versioned vers)
    {
        final Version v = vers.version();
        assertFalse("Should find version information (got "+v+")", v.isUknownVersion());
        assertEquals(MAJOR_VERSION, v.getMajorVersion());
        assertEquals(MINOR_VERSION, v.getMinorVersion());
        // Check patch level initially, comment out for maint versions
        assertEquals(0, v.getPatchLevel());
        assertEquals(GROUP_ID, v.getGroupId());
        assertEquals(ARTIFACT_ID, v.getArtifactId());
    }
}

