package com.kiselev.dataframework.ioc.exception;

import com.kiselev.dataframework.core.exception.DataFrameworkException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public class DataFrameWorkIoCException extends DataFrameworkException {

    public DataFrameWorkIoCException() {
    }

    public DataFrameWorkIoCException(String message) {
        super(message);
    }
}