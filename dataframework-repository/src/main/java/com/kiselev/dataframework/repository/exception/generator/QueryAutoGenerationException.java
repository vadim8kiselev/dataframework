package com.kiselev.dataframework.repository.exception.generator;

import com.kiselev.dataframework.repository.exception.DataFrameWorkRepositoryException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/11/2017
 */

public class QueryAutoGenerationException extends DataFrameWorkRepositoryException {

    public QueryAutoGenerationException() {
    }

    public QueryAutoGenerationException(String message) {
        super(message);
    }
}
