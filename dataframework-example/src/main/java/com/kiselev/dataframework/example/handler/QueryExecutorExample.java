package com.kiselev.dataframework.example.handler;

import com.kiselev.dataframework.example.entity.EntityExample;
import com.kiselev.dataframework.query.handler.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public class QueryExecutorExample extends QueryExecutor<List<EntityExample>> {

    @Override
    public List<EntityExample> transform(ResultSet resultSet) throws SQLException {
        List<EntityExample> result = new ArrayList<EntityExample>();
        while (resultSet.next()) {
            EntityExample entity = new EntityExample();

            entity.setId(resultSet.getLong("id"));
            entity.setName(resultSet.getString("name"));

            result.add(entity);
        }

        return result;
    }
}
