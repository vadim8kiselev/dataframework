package com.kiselev.dataframework.example.main;

import com.kiselev.dataframework.core.loader.impl.ConfigLoader;
import com.kiselev.dataframework.core.loader.impl.DataSourceLoader;
import com.kiselev.dataframework.core.resource.api.ResourceManager;
import com.kiselev.dataframework.core.resource.impl.ConfigManager;
import com.kiselev.dataframework.core.resource.impl.ConnectionManager;
import com.kiselev.dataframework.example.entity.EntityExample;
import com.kiselev.dataframework.example.handler.QueryExecutorExample;
import com.kiselev.dataframework.example.handler.QueryUpdaterExample;

import java.util.List;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public class Main {

    public static void main(String[] args) {

        ////////////////////////////////////////////////////////////
        ///Framework initialization/////////////////////////////////
        ResourceManager configManager = new ConfigManager();
        ResourceManager connectionManager = new ConnectionManager();

        ConfigLoader configLoader = new ConfigLoader();
        configLoader.setConfigManager(configManager);
        configLoader.load();

        DataSourceLoader dataSourceLoader = new DataSourceLoader();
        dataSourceLoader.setConfigManager(configManager);
        dataSourceLoader.setConnectionManager(connectionManager);
        dataSourceLoader.load();
        ////////////////////////////////////////////////////////////

        QueryExecutorExample executor = new QueryExecutorExample();
        executor.setConnectionManager(connectionManager);

        List<EntityExample> result = executor
                .executeQuery("SELECT * FROM test WHERE id < 8");

        for (EntityExample entity : result) {
            System.out.println("Table's row : " + entity.getId() + " " + entity.getName());
        }

        QueryUpdaterExample updater = new QueryUpdaterExample();
        updater.setConnectionManager(connectionManager);

        Integer affectedRows = updater
                .executeQuery("INSERT INTO test VALUES(8, 'Vadim')");
        System.out.println("Affected rows : " + affectedRows);
    }
}
