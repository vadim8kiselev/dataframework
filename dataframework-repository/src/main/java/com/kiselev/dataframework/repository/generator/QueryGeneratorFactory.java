package com.kiselev.dataframework.repository.generator;

import com.kiselev.dataframework.repository.annotation.Query;
import com.kiselev.dataframework.repository.exception.generator.QueryAutoGenerationException;
import com.kiselev.dataframework.repository.generator.api.QueryGenerator;
import com.kiselev.dataframework.repository.generator.impl.QueryAnnotationGenerator;

import java.lang.reflect.Method;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/11/2017
 */

public class QueryGeneratorFactory {

    public static QueryGenerator determineGenerator(Method method, Object[] arguments) {

        if (method.isAnnotationPresent(Query.class)) {
            return new QueryAnnotationGenerator(method, arguments);

        } else {
            throw new QueryAutoGenerationException("Cannot generate query by the method's construction");
        }
    }
}
