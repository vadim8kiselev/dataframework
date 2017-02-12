package com.kiselev.dataframework.repository.entity;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/11/2017
 */

public class QueryEntity {

    private String query;

    private QueryType queryType;

    public QueryEntity(String query, QueryType queryType) {
        this.query = query;
        this.queryType = queryType;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public QueryType getQueryType() {
        return queryType;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }
}
