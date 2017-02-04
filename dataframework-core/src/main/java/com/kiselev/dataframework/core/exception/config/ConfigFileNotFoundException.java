package com.kiselev.dataframework.core.exception.config;

import com.kiselev.dataframework.core.exception.DataFrameworkException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public class ConfigFileNotFoundException extends DataFrameworkException {

    public ConfigFileNotFoundException() {
    }

    public ConfigFileNotFoundException(String message) {
        super(message);
    }
}
