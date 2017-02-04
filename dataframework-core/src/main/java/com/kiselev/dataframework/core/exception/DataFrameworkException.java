package com.kiselev.dataframework.core.exception;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/04/2017
 */

public class DataFrameworkException extends RuntimeException {

    public DataFrameworkException() {
    }

    public DataFrameworkException(String message) {
        super(message);
    }
}
