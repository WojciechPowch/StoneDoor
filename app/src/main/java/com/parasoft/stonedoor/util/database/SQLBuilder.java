package com.parasoft.stonedoor.util.database;

import java.util.List;
import java.util.stream.IntStream;

public class SQLBuilder {
    private StringBuilder queryStringBuilder;

    private final String OPEN_QUOTE = "(";
    private final String CLOSE_QUOTE = ")";
    private final String COMMA = ", ";
    private final String SPACE = " ";
    private final String CREATE_TABLE = "CREATE TABLE";

    public String buildCreateTableQuery(String tableName, List<FieldConfig> fields) {
        try {
            initStringBuilder();
            initCreateTableQuery(tableName);
            buildFieldsQuery(fields);
            return queryStringBuilder.toString();
        } finally {
            cleanQueryStringBuilder();
        }
    }

    private void initStringBuilder() {
        this.queryStringBuilder = new StringBuilder();
    }

    private void initCreateTableQuery(String tableName) {
        queryStringBuilder.append(CREATE_TABLE);
        queryStringBuilder.append(SPACE);
        queryStringBuilder.append(tableName);
    }

    private void buildFieldsQuery(List<FieldConfig> fields) {
        final int lastIndex  = fields.size() - 1;
        queryStringBuilder.append(OPEN_QUOTE);
        IntStream.range(0, fields.size()).forEach( index -> {
            FieldConfig fieldConfig = fields.get(index);
            queryStringBuilder.append(fieldConfig.getFieldName());
            queryStringBuilder.append(SPACE);
            queryStringBuilder.append(fieldConfig.getFieldDataType());
            if (index < lastIndex) {
                queryStringBuilder.append(COMMA);
            }
        });
        queryStringBuilder.append(CLOSE_QUOTE);
    }

    private void cleanQueryStringBuilder() {
        this.queryStringBuilder = null;
    }
}
