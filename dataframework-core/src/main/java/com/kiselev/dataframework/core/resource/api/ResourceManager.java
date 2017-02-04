package com.kiselev.dataframework.core.resource.api;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/04/2017
 */

public interface ResourceManager {

    void initResource(Object resource);

    Object getResource();

    Object getResource(String key);
}
