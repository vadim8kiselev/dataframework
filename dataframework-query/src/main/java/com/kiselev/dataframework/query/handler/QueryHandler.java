package com.kiselev.dataframework.query.handler;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public interface QueryHandler<Entity> {

    Entity executeQuery(String executableQuery);

    Entity executePreparedQuery(String preparedQuery);

    Entity executeCallableQuery(String callableQuery);
}
