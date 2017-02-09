package com.kiselev.dataframework.mapper.processor.impl;

import com.kiselev.dataframework.mapper.annotation.Column;
import com.kiselev.dataframework.mapper.annotation.Table;
import com.kiselev.dataframework.mapper.exception.mapping.ClassMappingException;
import com.kiselev.dataframework.mapper.processor.api.Mapper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/10/2017
 */

public class QueryToEntityMapper<Entity> implements Mapper<ResultSet, Entity> {

    @Override
    @SuppressWarnings("unchecked")
    public Entity map(ResultSet resultSet) {
        Class<Entity> clazz = (Class<Entity>)
                ((ParameterizedType) getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0]; // FIXME

        Table table = clazz.getAnnotation(Table.class);
        if (table != null) {
            return mapEntity(resultSet, clazz);
        } else {
            throw new ClassMappingException("This query cannot be mapped");
        }
    }

    private Entity mapEntity(ResultSet resultSet, Class<Entity> clazz) {
        try {
            Object entity = clazz.newInstance();

            for (Field field : clazz.getDeclaredFields()) {
                Column column = field.getAnnotation(Column.class);
                if (column != null) {
                    String columnName = column.columnName();
                    Object result = resultSet.getObject(columnName);

                    field.setAccessible(true);
                    field.set(entity, result);
                }
            }
            return clazz.cast(entity);
        } catch (Exception exception) {
            throw new ClassMappingException("This query cannot be mapped");
        }
    }
}
