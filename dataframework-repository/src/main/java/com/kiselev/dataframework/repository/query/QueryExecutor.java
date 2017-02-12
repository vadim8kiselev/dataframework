package com.kiselev.dataframework.repository.query;

import com.kiselev.dataframework.mapper.processor.api.SQLMapper;
import com.kiselev.dataframework.mapper.processor.impl.SQLQueryMapper;
import com.kiselev.dataframework.repository.exception.query.ReturnTypeIsNotSupportedException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/12/2017
 */

public class QueryExecutor extends AbstractQueryHandler<ResultSet> {

    @Override
    public ResultSet execute(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Entity> Entity transform(ResultSet resultSet, Type returnType) throws SQLException {
        try {
            SQLMapper mapper = new SQLQueryMapper();

            if (returnType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) returnType;

                Class outerClass = (Class) parameterizedType.getRawType();
                if (Collection.class.isAssignableFrom(outerClass)) {
                    Class innerClass = (Class) parameterizedType.getActualTypeArguments()[0];

                    Collection collection;
                    if (!outerClass.isInterface()) {
                        collection = (Collection) outerClass.newInstance();
                    } else {
                        collection = getCollection(outerClass);
                    }

                    while (resultSet.next()) {
                        Object entity = mapper.map(resultSet, innerClass);
                        collection.add(entity);
                    }
                    return (Entity) collection;
                }

            } else if (returnType instanceof Class) {
                resultSet.next();
                return mapper.map(resultSet, (Class<Entity>) returnType);
            }

            throw new RuntimeException(); // stub
        } catch (Exception exception) {
            throw new ReturnTypeIsNotSupportedException("This return type is not supported");
        }
    }

    private Collection getCollection(Class collectionClass) {
        if (List.class.isAssignableFrom(collectionClass)) {
            return new ArrayList();
        } else if (Set.class.isAssignableFrom(collectionClass)) {
            return new HashSet();
        } else if (Queue.class.isAssignableFrom(collectionClass)) {
            return new ArrayDeque();
        } else {
            throw new ReturnTypeIsNotSupportedException("This generic return type is not supported");
        }
    }
}
