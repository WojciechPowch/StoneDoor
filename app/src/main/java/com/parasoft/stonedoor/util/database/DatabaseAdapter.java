package com.parasoft.stonedoor.util.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DatabaseAdapter {
    DatabaseHelper databaseHelper;

    public DatabaseAdapter(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    public void insertData(String tableName, @NonNull InsertData insertData) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = insertData.getContentValues();
        sqLiteDatabase.insert(tableName, null, contentValues);
    }

    public List<Map> getData(String tableName, List<FieldConfig> fields) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        String[] columns = getColumns(fields);
        Cursor cursor = sqLiteDatabase.query(tableName, columns,
                null, null, null, null, null);
        List result = new ArrayList();
        while (cursor.moveToNext()) {
            Map row = extractValuesFromCursor(cursor, fields);
            result.add(row);
        }
        return result;
    }

    public int delete(String tableName, Map<String, String> conditions) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        SQLiteWhereArgs conditionObject = new SQLiteWhereArgs(conditions);
        conditionObject.buildConfiguration();
        String query = conditionObject.getQuery();
        String[] whereArgs = conditionObject.getArguments();
        return database.delete(tableName, query, whereArgs);
    }

    public int update(String tableName, InsertData insertData, Map<String, String> conditions) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues contentValues = insertData.getContentValues();
        SQLiteWhereArgs conditionObject = new SQLiteWhereArgs(conditions);
        conditionObject.buildConfiguration();
        String query = conditionObject.getQuery();
        String[] whereArgs = conditionObject.getArguments();
        return database.update(tableName, contentValues, query, whereArgs);
    }

    private String[] getColumns(List<FieldConfig> fields) {
        List<String> fieldsNames = fields.stream().map(this::extractFieldName).collect(Collectors.toList());
        String[] castObject = new String[fieldsNames.size()];
        return fieldsNames.toArray(castObject);
    }

    private String extractFieldName(FieldConfig field) {
        return field.getFieldName();
    }

    private Map extractValuesFromCursor(Cursor cursor, List<FieldConfig> fieldConfigs) {
        return fieldConfigs.stream().map(fieldConfig -> {
            return extractColumnValueFromCursor(fieldConfig, cursor);
        }).collect(Collectors.toMap(valuePair -> valuePair.getKey() , valuePair -> valuePair.getValue()));
    }

    private ValuePair extractColumnValueFromCursor(FieldConfig fieldConfig, Cursor cursor) {
        String fieldName = fieldConfig.getFieldName();
        String fieldDataType = fieldConfig.getFieldDataType();
        ValuePair valuePair = new ValuePair();
        int columnIndex = cursor.getColumnIndex(fieldName);
        switch (fieldDataType) {
            case DatabaseVariables.INTEGER:
                Integer valueInt = cursor.getInt(columnIndex);
                valuePair = fillValuePair(fieldName, valueInt, valuePair);
                break;
            case DatabaseVariables.VARCHAR_255:
                String valueStr = cursor.getString(columnIndex);
                valuePair = fillValuePair(fieldName, valueStr, valuePair);
                break;
        }
        return valuePair;
    }

    private ValuePair fillValuePair(String key, Object value, ValuePair valuePair) {
        valuePair.setKey(key);
        valuePair.setValue(value);
        return valuePair;
    }
}
