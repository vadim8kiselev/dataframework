package com.kiselev.dataframework.query.handler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public abstract class QueryExecutor<Entity> extends AbstractQueryHandler<Entity, ResultSet> {

    @Override
    public ResultSet execute(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }
}
