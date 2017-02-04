package com.kiselev.dataframework.core.loader.impl;

import com.kiselev.dataframework.core.exception.datasource.ResourceValidationException;
import com.kiselev.dataframework.core.loader.api.Loader;
import com.kiselev.dataframework.core.resource.api.ResourceManager;
import org.apache.commons.dbcp.BasicDataSource;

import java.util.Map;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/04/2017
 */

public class DataSouceLoader implements Loader {

    private ResourceManager configManager;

    private ResourceManager connectionManager;

    public void load() {
        Map<String, String> mappedConfig = (Map<String, String>) configManager.getResource();
        if (isValidConfig(mappedConfig)) {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(mappedConfig.get("driver"));
            dataSource.setUrl(mappedConfig.get("url"));
            dataSource.setUsername(mappedConfig.get("login"));
            dataSource.setPassword(mappedConfig.get("password"));

            connectionManager.initResource(dataSource);
        } else {
            throw new ResourceValidationException("Config file is not valid for initializing of connection to data base");
        }
    }

    private boolean isValidConfig(Map<String, String> config) {
        return config.get("driver") != null &&
                config.get("url") != null &&
                config.get("login") != null &&
                config.get("password") != null;
    }

    public void setConfigManager(ResourceManager configManager) {
        this.configManager = configManager;
    }

    public void setConnectionManager(ResourceManager connectionManager) {
        this.connectionManager = connectionManager;
    }
}
