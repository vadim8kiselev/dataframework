package com.kiselev.dataframework.repository.exception.util;

import com.kiselev.dataframework.repository.exception.DataFrameWorkRepositoryException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/11/2017
 */

public class UnsupportedQueryException extends DataFrameWorkRepositoryException {

    public UnsupportedQueryException() {
    }

    public UnsupportedQueryException(String message) {
        super(message);
    }
}
