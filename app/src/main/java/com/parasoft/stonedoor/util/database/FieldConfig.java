package com.parasoft.stonedoor.util.database;

public class FieldConfig {
    private String fieldName;
    private String fieldDataType;

    public String getFieldName() {
        return fieldName;
    }

    public FieldConfig setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public String getFieldDataType() {
        return fieldDataType;
    }

    public FieldConfig setFieldDataType(String fieldDataType) {
        this.fieldDataType = fieldDataType;
        return this;
    }
}
