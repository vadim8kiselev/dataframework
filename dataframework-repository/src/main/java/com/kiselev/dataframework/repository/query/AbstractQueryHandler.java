package com.kiselev.dataframework.repository.query;

import com.kiselev.dataframework.core.resource.factory.ConnectionFactory;
import com.kiselev.dataframework.repository.exception.query.QueryExecutionException;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/12/2017
 */

public abstract class AbstractQueryHandler<Response> implements QueryHandler {

    @Override
    public <Entity> Entity executeQuery(String executableQuery, Type returnType) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(executableQuery)) {

            Response response = execute(statement);
            return transform(response, returnType);
        } catch (SQLException exception) {
            throw new QueryExecutionException("Cannot execute following query : \n" + executableQuery);
        }
    }

    @Override
    public <Entity> Entity executePreparedQuery(String preparedQuery, Type returnType) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(preparedQuery)) {

            Response response = execute(preparedStatement);
            return transform(response, returnType);
        } catch (SQLException exception) {
            throw new QueryExecutionException("Cannot execute following prepared query : \n" + preparedQuery);
        }
    }

    @Override
    public <Entity> Entity executeCallableQuery(String callableQuery, Type returnType) {
        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(callableQuery)) {

            Response response = execute(callableStatement);
            return transform(response, returnType);
        } catch (SQLException exception) {
            throw new QueryExecutionException("Cannot execute following callable query : \n" + callableQuery);
        }
    }

    public abstract <Entity> Entity transform(Response response, Type returnType) throws SQLException;

    public abstract Response execute(PreparedStatement statement) throws SQLException;
}
