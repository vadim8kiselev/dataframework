package com.kiselev.dataframework.ioc.exception.initialization;

import com.kiselev.dataframework.ioc.exception.DataFrameWorkIoCException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/04/2017
 */

public class ObjectIsAlreadyRegisteredException extends DataFrameWorkIoCException {

    public ObjectIsAlreadyRegisteredException() {
    }

    public ObjectIsAlreadyRegisteredException(String message) {
        super(message);
    }
}
