package com.kiselev.dataframework.core.resource.impl;

import com.kiselev.dataframework.core.resource.api.ResourceManager;

import javax.sql.DataSource;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/04/2017
 */

public class ConnectionManager implements ResourceManager<DataSource> {

    private static final ConnectionManager instance = new ConnectionManager();

    private DataSource dataSource;

    private ConnectionManager() {
        // private constructor
    }

    public static ConnectionManager getInstance() {
        return instance;
    }

    public void initResource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getResource() {
        return dataSource;
    }
}
