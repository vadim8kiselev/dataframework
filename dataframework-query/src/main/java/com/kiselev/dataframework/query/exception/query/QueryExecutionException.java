package com.kiselev.dataframework.query.exception.query;

import com.kiselev.dataframework.query.exception.DataFrameworkExecutorException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public class QueryExecutionException extends DataFrameworkExecutorException {

    public QueryExecutionException() {
    }

    public QueryExecutionException(String message) {
        super(message);
    }
}
