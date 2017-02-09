package com.kiselev.dataframework.mapper.processor.impl;

import com.kiselev.dataframework.mapper.annotation.Column;
import com.kiselev.dataframework.mapper.annotation.Table;
import com.kiselev.dataframework.mapper.exception.mapping.ClassMappingException;
import com.kiselev.dataframework.mapper.processor.api.Mapper;
import com.sun.deploy.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/10/2017
 */

public class EntityToQueryMapper<Entity> implements Mapper<Entity, String> {

    private static final String QUERY_PATTERN = "INSERT INTO %s (%s) VALUES (%s);";

    @Override
    public String map(Entity entity) {
        Class<?> clazz = entity.getClass();

        Table table = clazz.getAnnotation(Table.class);
        if (table != null) {
            String tableName = table.tableName();
            String columns = StringUtils.join(getColumns(entity), ", ");
            String values = StringUtils.join(getValues(entity), ", ");

            return String.format(QUERY_PATTERN, tableName, columns, values);
        } else {
            throw new ClassMappingException("This class cannot be mapped");
        }
    }

    private List<String> getColumns(Entity entity) {
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

    private List<String> getValues(Entity entity) {
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
}
