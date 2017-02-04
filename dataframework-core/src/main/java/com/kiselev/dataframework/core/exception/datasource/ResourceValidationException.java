package com.kiselev.dataframework.core.exception.datasource;

import com.kiselev.dataframework.core.exception.DataFrameworkException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public class ResourceValidationException extends DataFrameworkException {

    public ResourceValidationException() {
    }

    public ResourceValidationException(String message) {
        super(message);
    }
}
