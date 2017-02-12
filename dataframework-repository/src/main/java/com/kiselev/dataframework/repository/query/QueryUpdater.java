package com.kiselev.dataframework.repository.query;

import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/12/2017
 */

public class QueryUpdater extends AbstractQueryHandler<Integer> {

    @Override
    public Integer execute(PreparedStatement statement) throws SQLException {
        return statement.executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Entity> Entity transform(Integer integer, Type returnType) throws SQLException {
        return (Entity) integer;
    }
}
