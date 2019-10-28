package com.parasoft.stonedoor;

import com.parasoft.stonedoor.util.database.DatabaseVariables;
import com.parasoft.stonedoor.util.database.FieldConfig;
import com.parasoft.stonedoor.util.database.SQLBuilder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SQLBuilderUnitTest {
    private final String tableName = "TEST_TABLE";

    @Test
    public void createTableTest() {
        SQLBuilder sqlBuilder = new SQLBuilder();
        List<FieldConfig> fieldConfigs = new ArrayList() {{
            add(new FieldConfig().setFieldName("id").setFieldDataType(DatabaseVariables.INTEGER));
            add(new FieldConfig().setFieldName("login").setFieldDataType(DatabaseVariables.VARCHAR_255));
            add(new FieldConfig().setFieldName("password").setFieldDataType(DatabaseVariables.VARCHAR_255));
        }};
        String query = sqlBuilder.buildCreateTableQuery(tableName, fieldConfigs);
        assert query.equals("CREATE TABLE TEST_TABLE (id INTEGER, login VARCHAR(255), password VARCHAR(255))");
    }
}
