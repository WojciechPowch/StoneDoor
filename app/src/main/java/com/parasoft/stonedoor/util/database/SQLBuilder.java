package com.parasoft.stonedoor.util.database;

import java.util.List;

public class SQLBuilder {
    private StringBuilder queryStringBuilder;

    public String buildCreateTableQuery(String tableName, List<FieldConfig> fields) {
        try {
            initStringBuilder();
            return queryStringBuilder.toString();
        } finally {
            cleanQueryStringBuilder();
        }
    }

    private void initStringBuilder() {
        this.queryStringBuilder = new StringBuilder();
    }

    private void buildFieldsQuery(List<FieldConfig> fields) {

    }

    private void cleanQueryStringBuilder() {
        this.queryStringBuilder = null;
    }
}
