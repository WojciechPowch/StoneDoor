package com.parasoft.stonedoor.util.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private SQLBuilder sqlBuilder;

    public DatabaseHelper(Context context) {
        super(context,
                DatabaseVariables.DATABASE_NAME.getValue(),
                null,
                DatabaseVariables.DATABASE_VERSION.getValue());
        this.context = context;
        this.sqlBuilder = new SQLBuilder();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            provideCreateAction(db);
        } catch (Exception e) {
            System.out.println("Exception occured while creating a database");
        }
    }

    private void provideCreateAction(SQLiteDatabase db) {
        String createTableQuery = buildCreateTableQuery();
        db.execSQL(createTableQuery);
    }

    private String buildCreateTableQuery() {
        List<FieldConfig> fieldConfigs = buildFieldsConfig();
        return sqlBuilder.buildCreateTableQuery(DatabaseVariables.USER_TABLE.getValue(), fieldConfigs);
    }

    private List<FieldConfig> buildFieldsConfig() {
        return new ArrayList<FieldConfig>() {{
            add(DatabaseVariables.ID_COLUMN.getColumnConfig());
            add(DatabaseVariables.LOGIN_COLUMN.getColumnConfig());
            add(DatabaseVariables.PASSWORD_COLUMN.getColumnConfig());
        }};
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            System.out.println("On database upgrade");
            String dropTableQuery = buildDropTableQuery();
            db.execSQL(dropTableQuery);
            onCreate(db);
        } catch (Exception e) {
            System.out.println("Error while updating DataBase");
        }
    }

    private String buildDropTableQuery() {
        return sqlBuilder.createDropTableQuery(DatabaseVariables.USER_TABLE.getValue());
    }
}
