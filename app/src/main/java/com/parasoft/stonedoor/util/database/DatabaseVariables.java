package com.parasoft.stonedoor.util.database;

public enum DatabaseVariables {
    DATABASE_NAME {
        public String getValue() {
            return "STONE_DOOR";
        }
    },
    DATABASE_VERSION {
        public Integer getValue(){
            return 1;
        }
    },
    USER_TABLE {
        public String getValue() {
            return "USERS";
        }
    },
    ID_COLUMN {
        public FieldConfig getColumnConfig() {
            return new FieldConfig().
                    setFieldName("id").
                    setFieldDataType(PRIMARY_KEY);
        }
    },
    LOGIN_COLUMN {
        public FieldConfig getColumnConfig() {
            return new FieldConfig().
                    setFieldName("login").
                    setFieldDataType(VARCHAR_255);
        }
    },
    PASSWORD_COLUMN {
        public FieldConfig getColumnConfig() {
            return new FieldConfig().
                    setFieldName("password").
                    setFieldDataType(VARCHAR_255);
        }
    };

    public static final String PRIMARY_KEY = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final String INTEGER = "INTEGER";
    public static final String VARCHAR_255 = "VARCHAR(255)";

    public <T extends Object> T getValue() {
        return (T) emptyObject();
    }

    private Object emptyObject() {
        return new Object() {
            @Override
            public String toString() {
                return "Empty value";
            }
        };
    }

    public FieldConfig getColumnConfig() {
        return null;
    }
}
