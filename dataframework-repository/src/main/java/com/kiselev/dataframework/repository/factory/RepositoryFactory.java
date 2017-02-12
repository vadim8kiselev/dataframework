package com.kiselev.dataframework.repository.factory;

import com.kiselev.dataframework.repository.entity.QueryEntity;
import com.kiselev.dataframework.repository.generator.QueryGeneratorFactory;
import com.kiselev.dataframework.repository.generator.api.QueryGenerator;
import com.kiselev.dataframework.repository.util.QueryHandlerUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/11/2017
 */

public class RepositoryFactory {

    public static <Repository> Repository implement(final Class<Repository> repository) {
        return repository.cast(Proxy.newProxyInstance(
                repository.getClassLoader(),
                new Class<?>[]{repository},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
                        Type returnClass = method.getGenericReturnType();

                        QueryGenerator generator = QueryGeneratorFactory.determineGenerator(method, arguments);
                        QueryEntity queryEntity = generator.generate();

                        return QueryHandlerUtil.handleQuery(queryEntity, returnClass);
                    }
                }
        ));
    }
}
