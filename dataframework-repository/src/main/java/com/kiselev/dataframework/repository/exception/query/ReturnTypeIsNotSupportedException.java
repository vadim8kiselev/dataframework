package com.kiselev.dataframework.repository.exception.query;

import com.kiselev.dataframework.repository.exception.DataFrameWorkRepositoryException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/12/2017
 */

public class ReturnTypeIsNotSupportedException extends DataFrameWorkRepositoryException {

    public ReturnTypeIsNotSupportedException() {
    }

    public ReturnTypeIsNotSupportedException(String message) {
        super(message);
    }
}
