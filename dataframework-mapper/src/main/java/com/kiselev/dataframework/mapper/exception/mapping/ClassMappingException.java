package com.kiselev.dataframework.mapper.exception.mapping;

import com.kiselev.dataframework.mapper.exception.DataFrameworkMapperException;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/10/2017
 */

public class ClassMappingException extends DataFrameworkMapperException {

    public ClassMappingException() {
    }

    public ClassMappingException(String message) {
        super(message);
    }
}
