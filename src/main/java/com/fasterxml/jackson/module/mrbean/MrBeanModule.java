package com.fasterxml.jackson.module.mrbean;

import com.fasterxml.jackson.databind.module.SimpleModule;

import com.fasterxml.jackson.module.mrbean.AbstractTypeMaterializer;

public class MrBeanModule extends SimpleModule
{
    private static final long serialVersionUID = 1L;

    /**
     * Configured materializer instance to register with deserializer factory.
     */
    protected AbstractTypeMaterializer _materializer;
    
    /*
    /**********************************************************
    /* Life-cycle
    /**********************************************************
     */
    
    public MrBeanModule() {
        this(new AbstractTypeMaterializer());
    }

    public MrBeanModule(AbstractTypeMaterializer materializer) {
        super(PackageVersion.VERSION);
        _materializer = materializer;
    }
    
    @Override
    public void setupModule(SetupContext context)
    {
        super.setupModule(context);
        // All we really need to for now is to register materializer:
        context.addAbstractTypeResolver(_materializer);
    }

    /*
    /**********************************************************
    /* Extended API
    /**********************************************************
     */

    /**
     * Accessor for getting internal {@link AbstractTypeMaterializer}; usually
     * needed to call {@link AbstractTypeMaterializer#materializeClass}.
     */
    public AbstractTypeMaterializer getMaterializer() {
        return _materializer;
    }
}
