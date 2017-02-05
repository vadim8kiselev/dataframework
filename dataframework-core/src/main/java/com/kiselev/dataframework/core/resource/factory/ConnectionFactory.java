package com.kiselev.dataframework.core.resource.factory;

import com.kiselev.dataframework.core.exception.datasource.ConnectionInitializeException;
import com.kiselev.dataframework.core.loader.api.Loader;
import com.kiselev.dataframework.core.loader.impl.CoreLoader;
import com.kiselev.dataframework.core.resource.impl.ConnectionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public class ConnectionFactory {

    private static final DataSource dataSource;

    static {
        Loader core = new CoreLoader();
        if (!core.isLoaded()) {
            core.load();
        }

        dataSource = ConnectionManager.getInstance().getResource();
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new ConnectionInitializeException("Cannot initialize connection");
        }
    }
}
