package com.kiselev.dataframework.ioc.container;

import com.kiselev.dataframework.ioc.exception.initialization.ObjectIsAlreadyRegisteredException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/04/2017
 */

public class ObjectsContainer {

    private static final ObjectsContainer instance = new ObjectsContainer();

    private static final Map<String, Object> container = new HashMap<String, Object>();

    private ObjectsContainer() {
        // private constructor
    }

    public static ObjectsContainer getInstance() {
        return instance;
    }

    public void initObject(String objectName, Object object) {
        if (container.keySet().contains(objectName)) {
            throw new ObjectIsAlreadyRegisteredException("Object with following name was already registered : " + objectName);
        }
        container.put(objectName, object);
    }

    public Object getObject(String objectName) {
        return container.get(objectName);
    }

    public <Type> Type getObject(String objectName, Class<Type> objectClass) {
        try {
            return objectClass.cast(container.get(objectName));
        } catch (ClassCastException exception) {
            return null;
        }
    }
}
