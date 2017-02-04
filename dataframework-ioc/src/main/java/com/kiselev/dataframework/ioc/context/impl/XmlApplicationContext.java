package com.kiselev.dataframework.ioc.context.impl;

import com.kiselev.dataframework.ioc.container.ObjectsContainer;
import com.kiselev.dataframework.ioc.context.ApplicationContext;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public class XmlApplicationContext implements ApplicationContext {

    private static final XmlApplicationContext instance = new XmlApplicationContext();

    private ObjectsContainer container = ObjectsContainer.getInstance();

    private XmlApplicationContext() {
        // private constructor
        load();
    }

    public static XmlApplicationContext getInstance() {
        return instance;
    }

    private void load() {
        // TODO
    }

    @Override
    public Object getObject(String objectName) {
        return container.getObject(objectName);
    }

    @Override
    public <Type> Type getObject(String objectName, Class<Type> objectClass) {
        return container.getObject(objectName, objectClass);
    }
}
