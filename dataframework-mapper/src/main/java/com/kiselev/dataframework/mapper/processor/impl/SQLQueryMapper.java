package com.kiselev.dataframework.mapper.processor.impl;

import com.kiselev.dataframework.mapper.annotation.Column;
import com.kiselev.dataframework.mapper.annotation.Table;
import com.kiselev.dataframework.mapper.exception.mapping.ClassMappingException;
import com.kiselev.dataframework.mapper.processor.api.SQLMapper;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/11/2017
 */

public class SQLQueryMapper implements SQLMapper {

    @Override
    public <Entity> String map(Entity entity) {
        final String QUERY_PATTERN = "INSERT INTO %s (%s) VALUES (%s);";
        Class<?> clazz = entity.getClass();

        Table table = clazz.getAnnotation(Table.class);
        if (table != null) {
            String tableName = table.tableName();
            String columns = StringUtils.join(getColumnsFromEntity(entity), ", ");
            String values = StringUtils.join(getValuesFromEntity(entity), ", ");

            return String.format(QUERY_PATTERN, tableName, columns, values);
        } else {
            throw new ClassMappingException("This class cannot be mapped");
        }
    }

    @Override
    public <JavaData> JavaData map(ResultSet resultSet, Class<JavaData> clazz) {

        Table table = clazz.getAnnotation(Table.class);
        if (table != null) {
            return getEntityFromResultSet(resultSet, clazz);
        } else {
            throw new ClassMappingException("This query cannot be mapped");
        }
    }

    private <Entity> List<String> getColumnsFromEntity(Entity entity) {
        Class<?> clazz = entity.getClass();
        List<String> columnsList = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                String columnName = column.columnName();
                columnsList.add(columnName);
            }
        }
        return columnsList;
    }

    private <Entity> List<String> getValuesFromEntity(Entity entity) {
        Class<?> clazz = entity.getClass();
        List<String> valuesList = new ArrayList<>();
        try {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true);
                    Object result = field.get(entity);
                    if (result != null) {
                        if (field.getType().isAssignableFrom(String.class)) {
                            result = String.format("'%s'", result);
                        }
                    } else {
                        result = "NULL";
                    }
                    valuesList.add(result.toString());
                }
            }
            return valuesList;
        } catch (ReflectiveOperationException exception) {
            throw new ClassMappingException("This class cannot be mapped");
        }
    }

    private <Entity> Entity getEntityFromResultSet(ResultSet resultSet, Class<Entity> clazz) {
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
