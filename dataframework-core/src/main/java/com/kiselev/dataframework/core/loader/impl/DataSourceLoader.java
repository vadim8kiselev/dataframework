package com.kiselev.dataframework.core.loader.impl;

import com.kiselev.dataframework.core.exception.datasource.ResourceValidationException;
import com.kiselev.dataframework.core.loader.api.Loader;
import com.kiselev.dataframework.core.resource.api.ResourceManager;
import com.kiselev.dataframework.core.resource.impl.ConfigManager;
import com.kiselev.dataframework.core.resource.impl.ConnectionManager;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/04/2017
 */

public class DataSourceLoader implements Loader {

    private static boolean loaded = false;

    @Override
    public void load() {
        if (!isLoaded()) {
            Map<String, String> mappedConfig = ConfigManager.getInstance().getResource();
            if (isValidConfig(mappedConfig)) {
                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setDriverClassName(mappedConfig.get("driver"));
                dataSource.setUrl(mappedConfig.get("url"));
                dataSource.setUsername(mappedConfig.get("login"));
                dataSource.setPassword(mappedConfig.get("password"));

                ResourceManager<DataSource> connectionManager = ConnectionManager.getInstance();
                connectionManager.initResource(dataSource);

                loaded = true;
            } else {
                throw new ResourceValidationException("Config file is not valid for initializing of connection to data base");
            }
        }
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }

    private boolean isValidConfig(Map<String, String> config) {
        return config.get("driver") != null &&
                config.get("url") != null &&
                config.get("login") != null &&
                config.get("password") != null;
    }
}
