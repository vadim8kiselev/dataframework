package com.kiselev.dataframework.repository.generator.impl;

import com.kiselev.dataframework.repository.annotation.Param;
import com.kiselev.dataframework.repository.annotation.Query;
import com.kiselev.dataframework.repository.entity.QueryEntity;
import com.kiselev.dataframework.repository.entity.QueryType;
import com.kiselev.dataframework.repository.generator.api.QueryGenerator;
import com.kiselev.dataframework.repository.util.QueryTypeDeterminator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/11/2017
 */

public class QueryAnnotationGenerator implements QueryGenerator {

    private Method method;

    private Object[] arguments;

    public QueryAnnotationGenerator(Method method, Object[] arguments) {
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public QueryEntity generate() {
        Query queryAnnotation = method.getAnnotation(Query.class);
        String pattern = queryAnnotation.value();

        Param[] annotations = filterParametersAnnotations(method);
        for (int index = 0; index < arguments.length; index++) {
            Param annotation = annotations[index];
            Object value = arguments[index];

            pattern = pattern.replace(":" + annotation.value(), value.toString());
        }

        String query = pattern;
        QueryType type = QueryTypeDeterminator.determineQueryType(query);

        return new QueryEntity(pattern, type);
    }

    private Param[] filterParametersAnnotations(Method method) {
        Annotation[][] annotations = method.getParameterAnnotations();

        Param[] parameters = new Param[annotations.length];
        for (int index = 0; index < annotations.length; index++) {
            for (Annotation annotation : annotations[index]) {
                if (annotation instanceof Param) {
                    parameters[index] = (Param) annotation;
                }
            }
        }

        return parameters;
    }
}
