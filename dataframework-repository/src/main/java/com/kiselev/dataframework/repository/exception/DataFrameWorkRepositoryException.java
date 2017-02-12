package com.kiselev.dataframework.repository.exception;

import com.kiselev.dataframework.core.exception.DataFrameworkException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/11/2017
 */

public class DataFrameWorkRepositoryException extends DataFrameworkException {

    public DataFrameWorkRepositoryException() {
    }

    public DataFrameWorkRepositoryException(String message) {
        super(message);
    }
}
