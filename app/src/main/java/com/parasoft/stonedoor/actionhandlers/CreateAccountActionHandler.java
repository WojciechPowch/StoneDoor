package com.parasoft.stonedoor.actionhandlers;

import android.content.Context;
import android.provider.ContactsContract;

import com.parasoft.stonedoor.util.database.DatabaseAdapter;
import com.parasoft.stonedoor.util.database.DatabaseVariables;
import com.parasoft.stonedoor.util.database.InsertData;

public class CreateAccountActionHandler {
    private Context context;

    public CreateAccountActionHandler(Context context) {
        this.context = context;
    }

    public void insertNewAccount(String login, String password) {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(context);
        InsertData insertData = new InsertData();
        insertData.setInsertedPairString(
                DatabaseVariables.LOGIN_COLUMN.getColumnConfig().getFieldName(),
                login
        );
        insertData.setInsertedPairString(
                DatabaseVariables.PASSWORD_COLUMN.getColumnConfig().getFieldName(),
                password
        );
        databaseAdapter.insertData(DatabaseVariables.USER_TABLE.getValue(), insertData);
    }
}
