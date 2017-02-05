package com.kiselev.dataframework.example.handler;

import com.kiselev.dataframework.query.handler.QueryUpdater;

import java.sql.SQLException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public class QueryUpdaterExample extends QueryUpdater<Integer> {

    @Override
    public Integer transform(Integer integer) throws SQLException {
        return integer;
    }
}
