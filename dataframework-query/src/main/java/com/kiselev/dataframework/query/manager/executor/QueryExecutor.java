package com.kiselev.dataframework.query.manager.executor;

import com.kiselev.dataframework.core.resource.impl.ConnectionManager;
import com.kiselev.dataframework.query.exception.query.QueryExecutionException;
import com.kiselev.dataframework.query.manager.handler.QueryHandler;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public abstract class QueryExecutor implements QueryHandler {

    private ConnectionManager connectionManager;

    public <Response> Response executeQuery(String query) {
        try (Connection connection = connectionManager.getResource();
             PreparedStatement statement = connection.prepareStatement(query)) {

            return execute(statement);
        } catch (SQLException exception) {
            throw new QueryExecutionException("Cannot execute following query : \n" + query);
        }
    }

    public <Response> Response executePreparedQuery(String preparedQuery) {
        try (Connection connection = connectionManager.getResource();
             PreparedStatement preparedStatement = connection.prepareStatement(preparedQuery)) {

            fillStatement(preparedStatement);

            return execute(preparedStatement);
        } catch (SQLException exception) {
            throw new QueryExecutionException("Cannot execute following prepared query : \n" + preparedQuery);
        }
    }

    public <Response> Response executeCallableQuery(String callableQuery) {
        try (Connection connection = connectionManager.getResource();
             CallableStatement callableStatement = connection.prepareCall(callableQuery)) {

            fillStatement(callableStatement);

            return execute(callableStatement);
        } catch (SQLException exception) {
            throw new QueryExecutionException("Cannot execute following callable query : \n" + callableQuery);
        }
    }

    public <Response> Response execute(PreparedStatement statement) throws SQLException {
        return (Response) transform(statement.executeQuery());
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
}
