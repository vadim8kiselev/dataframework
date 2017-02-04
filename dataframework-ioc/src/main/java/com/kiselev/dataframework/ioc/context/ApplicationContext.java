package com.kiselev.dataframework.ioc.context;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public interface ApplicationContext {

    Object getObject(String objectName);

    <Type> Type getObject(String objectName, Class<Type> objectClass);
}
