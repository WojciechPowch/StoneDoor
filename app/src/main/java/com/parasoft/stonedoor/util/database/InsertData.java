package com.parasoft.stonedoor.util.database;

import android.content.ContentValues;

public class InsertData {
    ContentValues contentValues;

    public InsertData() {
        contentValues = new ContentValues();
    }

    public InsertData setInsertedPairString(String columnName, String value) {
        contentValues.put(columnName, value);
        return this;
    }

    public ContentValues getContentValues() {
        return contentValues;
    }
}
