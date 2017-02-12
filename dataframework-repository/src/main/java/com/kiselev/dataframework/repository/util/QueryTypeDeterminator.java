package com.kiselev.dataframework.repository.util;

import com.kiselev.dataframework.repository.entity.QueryType;
import com.kiselev.dataframework.repository.exception.util.UnsupportedQueryException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/11/2017
 */

public class QueryTypeDeterminator {

    private static final Map<String, QueryType> dictionary = new HashMap<String, QueryType>() {
        {
            put("SELECT", QueryType.EXECUTE);
            put("CALL", QueryType.EXECUTE);

            put("UPDATE", QueryType.UPDATE);
            put("INSERT", QueryType.UPDATE);
            put("DELETE", QueryType.UPDATE);
            put("DROP", QueryType.UPDATE);
        }
    };

    public static QueryType determineQueryType(String query) {
        try {
            String typeCommand = query.split(" ")[0].toUpperCase();

            QueryType type = dictionary.get(typeCommand);
            if (type != null) {
                return type;
            }

            throw new RuntimeException(); // stub
        } catch (Exception exception) {
            throw new UnsupportedQueryException("Unsupported query");
        }
    }
}
