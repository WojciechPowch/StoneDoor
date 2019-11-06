package com.parasoft.stonedoor.helpers;

import android.content.Context;

import com.parasoft.stonedoor.util.database.DatabaseAdapter;
import com.parasoft.stonedoor.util.database.DatabaseVariables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivityHelper {
    private Context context;

    public MainActivityHelper(Context context) {
        this.context = context;
    }

    public boolean checkIfAnyUserExists() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(this.context);
        List<Map> result = databaseAdapter.getData(DatabaseVariables.USER_TABLE.getValue(),
                new ArrayList() {{
            add(DatabaseVariables.LOGIN_COLUMN.getColumnConfig());
        }});
        return result.size() == 0;
    }
}
