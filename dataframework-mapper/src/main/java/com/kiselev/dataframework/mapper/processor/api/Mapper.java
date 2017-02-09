package com.kiselev.dataframework.mapper.processor.api;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/10/2017
 */

public interface Mapper<From, To> {

    To map(From something);
}
