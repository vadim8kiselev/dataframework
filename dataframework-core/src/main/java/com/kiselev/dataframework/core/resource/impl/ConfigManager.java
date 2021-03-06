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

public class ConfigManager implements ResourceManager<Map<String, String>> {

    private static final ConfigManager instance = new ConfigManager();

    private Map<String, String> config = new HashMap<String, String>();

    private ConfigManager() {
        // private constructor
    }

    public static ConfigManager getInstance() {
        return instance;
    }

    public void initResource(Map<String, String> config) {
        try {
            this.config.putAll(config);
        } catch (ClassCastException exception) {
            throw new ResourceInitializingException("Config manager cannot initialize config");
        }
    }

    public Map<String, String> getResource() {
        return config;
    }
}
