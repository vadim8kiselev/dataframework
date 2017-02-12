package com.kiselev.dataframework.repository.query;

import java.lang.reflect.Type;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/12/2017
 */

public interface QueryHandler {

    <Entity> Entity executeQuery(String executableQuery, Type returnType);

    <Entity> Entity executePreparedQuery(String preparedQuery, Type returnType);

    <Entity> Entity executeCallableQuery(String callableQuery, Type returnType);
}
