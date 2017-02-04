package com.kiselev.dataframework.query.manager.handler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public interface QueryHandler {

    <Entity, Response> Entity transform(Response response) throws SQLException;

    <Response> Response execute(PreparedStatement preparedStatement) throws SQLException;

    void fillStatement(PreparedStatement preparedStatement) throws SQLException;
}
