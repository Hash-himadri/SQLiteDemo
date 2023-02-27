package com.example.sqlitedemo_b;

import android.provider.BaseColumns;

public class TableData {
    public static abstract class TableInfo implements BaseColumns{
        public static final String DATABASE_NAME = "Students";
        public static final String TABLE_NAME = "reg_info";
        public static final String NAME = "name";
        public static final String SURNAME = "surname";
        public static final String ID = "id";
        public static final String MARKS = "marks";
    }
}
