package com.fasterxml.jackson.module.mrbean;

import com.fasterxml.jackson.core.Version;

import com.fasterxml.jackson.databind.Module;

import com.fasterxml.jackson.module.mrbean.AbstractTypeMaterializer;

public class MrBeanModule extends Module
{
    private final String NAME = "MrBeanModule";

    // TODO: externalize
    private final static Version VERSION = new Version(2, 0, 0, null);

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
        _materializer = materializer;
    }

    @Override public String getModuleName() { return NAME; }
    @Override public Version version() { return VERSION; }
    
    @Override
    public void setupModule(SetupContext context)
    {
        // All we really need to for now is to register materializer:
        context.addAbstractTypeResolver(_materializer);
    }

    /*
    /**********************************************************
    /* Extended API, configuration
    /**********************************************************
     */
}
