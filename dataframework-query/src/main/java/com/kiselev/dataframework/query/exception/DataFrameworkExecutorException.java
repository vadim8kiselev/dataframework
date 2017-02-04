package com.kiselev.dataframework.query.exception;

import com.kiselev.dataframework.core.exception.DataFrameworkException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public class DataFrameworkExecutorException extends DataFrameworkException {

    public DataFrameworkExecutorException() {
    }

    public DataFrameworkExecutorException(String message) {
        super(message);
    }
}
