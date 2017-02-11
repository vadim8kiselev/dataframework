package com.kiselev.dataframework.mapper.processor.api;

import java.sql.ResultSet;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/11/2017
 */

public interface SQLMapper {

    <Entity> Entity map(ResultSet externalData, Class<Entity> clazz);

    <Entity> String map(Entity externalData);
}
