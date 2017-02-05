package com.kiselev.dataframework.query.handler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public abstract class QueryUpdater<Entity> extends AbstractQueryHandler<Entity, Integer> {

    @Override
    public Integer execute(PreparedStatement statement) throws SQLException {
        return statement.executeUpdate();
    }
}
