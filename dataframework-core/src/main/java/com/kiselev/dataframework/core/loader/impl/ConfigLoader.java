package com.kiselev.dataframework.core.loader.impl;

import com.kiselev.dataframework.core.exception.config.ConfigFileNotFoundException;
import com.kiselev.dataframework.core.exception.config.ResourceInitializingException;
import com.kiselev.dataframework.core.loader.api.Loader;
import com.kiselev.dataframework.core.resource.api.ResourceManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/04/2017
 */

public class ConfigLoader implements Loader {

    private ResourceManager configManager;

    public void load() {
        Properties properties = new Properties();

        try (InputStream stream = ConfigLoader.class.getClassLoader().getResourceAsStream("persistence.properties")) {
            if (stream != null) {
                properties.load(stream);
            } else {
                throw new ConfigFileNotFoundException("Config file is not found");
            }

            Map<String, String> config = createConfig(properties);
            configManager.initResource(config);

        } catch (IOException e) {
            throw new ResourceInitializingException("Config file cannot be loaded");
        }
    }

    private Map<String, String> createConfig(Properties properties) {
        Map<String, String> config = new HashMap<String, String>();

        for (String key : properties.stringPropertyNames()) {
            config.put(key, properties.getProperty(key));
        }

        return config;
    }

    public void setConfigManager(ResourceManager configManager) {
        this.configManager = configManager;
    }
}
