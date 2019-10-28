package com.parasoft.stonedoor;

import com.parasoft.stonedoor.util.database.SQLiteWhereArgs;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SQLiteWhereArgsTest {
    private String login = "test";
    private String name = "condition";

    @Test
    public void whereQueryTest() {
        SQLiteWhereArgs sqLiteWhereArgs = prepareTestObject();
        String query = sqLiteWhereArgs.getQuery();
        assert query.equals("login = ?, name = ?");
    }

    @Test
    public void whereArgsTest() {
        SQLiteWhereArgs sqLiteWhereArgs = prepareTestObject();
        String[] arguments = sqLiteWhereArgs.getArguments();
        assert arguments[0].equals(login);
        assert arguments[1].equals(name);
    }

    private SQLiteWhereArgs prepareTestObject() {
        Map<String, String> config = new LinkedHashMap() {{
            put("login", login);
            put("name", name);
        }};
        SQLiteWhereArgs sqLiteWhereArgs = new SQLiteWhereArgs(config);
        sqLiteWhereArgs.buildConfiguration();
        return sqLiteWhereArgs;
    }
}
