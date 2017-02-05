package com.kiselev.dataframework.core.loader.impl;

import com.kiselev.dataframework.core.loader.api.Loader;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public class CoreLoader implements Loader {

    private static boolean loaded = false;

    @Override
    public void load() {
        if (!isLoaded()) {
            // order is important
            new ConfigLoader().load();
            new DataSourceLoader().load();

            loaded = true;
        }
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }
}
