package com.kiselev.dataframework.core.resource.impl;

import com.kiselev.dataframework.core.exception.config.ResourceInitializingException;
import com.kiselev.dataframework.core.resource.api.ResourceManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/04/2017
 */

public class ConfigManager implements ResourceManager {

    private Map<String, String> config = new HashMap<String, String>();

    public void initResource(Object resource) {
        try {
            config.putAll((Map<String, String>) resource);
        } catch (ClassCastException exception) {
            throw new ResourceInitializingException("Config manager cannot initialize config");
        }
    }

    public Map<String, String> getResource() {
        return config;
    }

    public String getResource(String key) {
        return config.get(key);
    }
}
