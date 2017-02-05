package com.kiselev.dataframework.example.main;

import com.kiselev.dataframework.example.entity.EntityExample;
import com.kiselev.dataframework.example.handler.QueryExecutorExample;
import com.kiselev.dataframework.example.handler.QueryUpdaterExample;
import com.kiselev.dataframework.query.handler.QueryHandler;

import java.util.List;

/**
 * @author Vadim Kiselev
 * @version 1.0
 * @since 02/05/2017
 */

public class Main {

    public static void main(String[] args) {
        QueryHandler<List<EntityExample>> executor = new QueryExecutorExample();

        List<EntityExample> result = executor
                .executeQuery("SELECT * FROM test WHERE id <> 8");

        for (EntityExample entity : result) {
            System.out.println("Table's row : " + entity.getId() + " " + entity.getName());
        }

        QueryHandler<Integer> updater = new QueryUpdaterExample();

        Integer affectedRows = updater
                .executeQuery("INSERT INTO test VALUES(8, 'Vadim')");
        System.out.println("Affected rows : " + affectedRows);
    }
}
