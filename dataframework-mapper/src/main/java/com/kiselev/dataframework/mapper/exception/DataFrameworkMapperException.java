package com.kiselev.dataframework.mapper.exception;

import com.kiselev.dataframework.core.exception.DataFrameworkException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/10/2017
 */

public class DataFrameworkMapperException extends DataFrameworkException {

    public DataFrameworkMapperException() {
    }

    public DataFrameworkMapperException(String message) {
        super(message);
    }
}
