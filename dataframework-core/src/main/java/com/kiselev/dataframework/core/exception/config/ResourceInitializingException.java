package com.kiselev.dataframework.core.exception.config;

import com.kiselev.dataframework.core.exception.DataFrameworkException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public class ResourceInitializingException extends DataFrameworkException {

    public ResourceInitializingException() {
    }

    public ResourceInitializingException(String message) {
        super(message);
    }
}
