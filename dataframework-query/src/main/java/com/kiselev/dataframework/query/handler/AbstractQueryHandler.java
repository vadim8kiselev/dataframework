package com.kiselev.dataframework.query.handler;

import com.kiselev.dataframework.core.resource.api.ResourceManager;
import com.kiselev.dataframework.query.exception.query.QueryExecutionException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public abstract class AbstractQueryHandler<Entity, Response> implements QueryHandler<Entity> {

    private ResourceManager connectionManager;

    @Override
    public Entity executeQuery(String executableQuery) {
        try (Connection connection = (Connection) connectionManager.getResource();
             PreparedStatement statement = connection.prepareStatement(executableQuery)) {

            Response response = execute(statement);
            return transform(response);
        } catch (SQLException exception) {
            throw new QueryExecutionException("Cannot execute following query : \n" + executableQuery);
        }
    }

    @Override
    public Entity executePreparedQuery(String preparedQuery) {
        try (Connection connection = (Connection) connectionManager.getResource();
             PreparedStatement preparedStatement = connection.prepareStatement(preparedQuery)) {

            fillStatement(preparedStatement);

            Response response = execute(preparedStatement);
            return transform(response);
        } catch (SQLException exception) {
            throw new QueryExecutionException("Cannot execute following prepared query : \n" + preparedQuery);
        }
    }

    @Override
    public Entity executeCallableQuery(String callableQuery) {
        try (Connection connection = (Connection) connectionManager.getResource();
             CallableStatement callableStatement = connection.prepareCall(callableQuery)) {

            fillStatement(callableStatement);

            Response response = execute(callableStatement);
            return transform(response);
        } catch (SQLException exception) {
            throw new QueryExecutionException("Cannot execute following callable query : \n" + callableQuery);
        }
    }

    public void fillStatement(PreparedStatement preparedStatement) throws SQLException {
        // default realization
    }

    public abstract Entity transform(Response response) throws SQLException;

    public abstract Response execute(PreparedStatement statement) throws SQLException;

    public void setConnectionManager(ResourceManager connectionManager) {
        this.connectionManager = connectionManager;
    }
}
