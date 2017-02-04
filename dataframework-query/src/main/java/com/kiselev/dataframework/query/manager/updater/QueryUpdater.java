package com.kiselev.dataframework.query.manager.updater;

import com.kiselev.dataframework.query.manager.executor.QueryExecutor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public abstract class QueryUpdater extends QueryExecutor {

    @Override
    public <Response> Response execute(PreparedStatement statement) throws SQLException {
        return (Response) new Integer(statement.executeUpdate());
    }
}
