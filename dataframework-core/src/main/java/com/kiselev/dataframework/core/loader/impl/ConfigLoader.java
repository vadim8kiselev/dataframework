package com.kiselev.dataframework.core.loader.impl;

import com.kiselev.dataframework.core.exception.config.ConfigFileNotFoundException;
import com.kiselev.dataframework.core.exception.config.ResourceInitializingException;
import com.kiselev.dataframework.core.loader.api.Loader;
import com.kiselev.dataframework.core.resource.api.ResourceManager;
import com.kiselev.dataframework.core.resource.impl.ConfigManager;

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

    private static boolean loaded = false;

    @Override
    public void load() {
        if (!isLoaded()) {
            Properties properties = new Properties();

            try (InputStream stream = ConfigLoader.class.getClassLoader().getResourceAsStream("persistence.properties")) {
                if (stream != null) {
                    properties.load(stream);
                } else {
                    throw new ConfigFileNotFoundException("Config file is not found");
                }

                Map<String, String> config = createConfig(properties);
                ResourceManager<Map<String, String>> configManager = ConfigManager.getInstance();
                configManager.initResource(config);

                loaded = true;

            } catch (IOException e) {
                throw new ResourceInitializingException("Config file cannot be loaded");
            }
        }
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }

    private Map<String, String> createConfig(Properties properties) {
        Map<String, String> config = new HashMap<String, String>();

        for (String key : properties.stringPropertyNames()) {
            config.put(key, properties.getProperty(key));
        }

        return config;
    }
}
