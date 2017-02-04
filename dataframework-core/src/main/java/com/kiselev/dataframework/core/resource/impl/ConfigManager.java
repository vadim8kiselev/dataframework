package com.kiselev.dataframework.core.resource.impl;

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
            // TODO
        }
    }

    public Map<String, String> getResource() {
        return config;
    }

    public String getResource(String key) {
        return config.get(key);
    }
}
