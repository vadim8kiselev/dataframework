package com.kiselev.dataframework.core.resource.impl;

import com.kiselev.dataframework.core.resource.api.ResourceManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/04/2017
 */

public class ConnectionManager implements ResourceManager {

    private DataSource dataSource;

    public void initResource(Object resource) {
        try {
            dataSource = (DataSource) resource;
        } catch (ClassCastException exception) {
            // TODO
        }
    }

    public Connection getResource() {
        try {
            return dataSource.getConnection();
        } catch (SQLException exception) {
            return null;
        }
    }

    public Connection getResource(String key) {
        // key is not supported here
        return getResource();
    }
}
