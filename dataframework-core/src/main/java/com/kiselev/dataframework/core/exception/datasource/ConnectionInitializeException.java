package com.kiselev.dataframework.core.exception.datasource;

import com.kiselev.dataframework.core.exception.DataFrameworkException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public class ConnectionInitializeException extends DataFrameworkException {

    public ConnectionInitializeException() {
    }

    public ConnectionInitializeException(String message) {
        super(message);
    }
}
