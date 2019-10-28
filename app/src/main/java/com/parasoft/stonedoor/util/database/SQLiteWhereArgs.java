package com.parasoft.stonedoor.util.database;

import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

public class SQLiteWhereArgs {
    private Map<String, String> config;
    private String query;
    private String[] arguments;
    private String[] fieldsArr;

    public SQLiteWhereArgs(Map<String, String> config) {
        this.config = config;
        this.query = "";
    }

    public void buildConfiguration() {
        buildQuery();
        buildArguments();
    }

    public String getQuery() {
        return this.query;
    }

    public String[] getArguments() {
        return this.arguments;
    }

    private void buildQuery() {
        Set<String> fields = config.keySet();
        fieldsArr = new String[fields.size()];
        fieldsArr = fields.toArray(fieldsArr);
        int lastIndex = fieldsArr.length - 1;
        IntStream.range(0, fieldsArr.length).forEach(
                index -> {
                    String field = fieldsArr[index];
                    query += field + " = ?";
                    if (index < lastIndex) {
                        query += ", ";
                    }
                }
        );
    }

    private void buildArguments() {
        arguments = new String[config.size()];
        for (int i = 0; i < fieldsArr.length; i++) {
            String key = fieldsArr[i];
            arguments[i] = config.get(key);
        }
    }
}
