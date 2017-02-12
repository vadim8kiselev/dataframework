package com.kiselev.dataframework.repository.util;

import com.kiselev.dataframework.repository.entity.QueryEntity;
import com.kiselev.dataframework.repository.entity.QueryType;
import com.kiselev.dataframework.repository.exception.util.UnsupportedQueryException;
import com.kiselev.dataframework.repository.query.QueryExecutor;
import com.kiselev.dataframework.repository.query.QueryUpdater;

import java.lang.reflect.Type;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/12/2017
 */

public class QueryHandlerUtil {

    public static Object handleQuery(QueryEntity queryEntity, Type returnClass) {

        QueryType queryType = queryEntity.getQueryType();
        String query = queryEntity.getQuery();

        switch (queryType) {
            case EXECUTE:
                // call execute
                return new QueryExecutor().executeQuery(query, returnClass);

            case UPDATE:
                // call update
                return new QueryUpdater().executeQuery(query, returnClass);

            default:
                throw new UnsupportedQueryException("Unexpected exception");
        }
    }
}
