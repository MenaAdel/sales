package com.example.menaadel.salesapp.DataStorage.sqLite;

import android.provider.BaseColumns;

import java.sql.Date;

/**
 * Created by MenaAdel on 1/17/2018.
 */

public class TableData {
    public TableData() {
    }

    public static abstract class TableInfo implements BaseColumns {
        public static final String DATABASE_NAME = "salesdb";
        public static final String TABLE_NAME = "salestable";
        public static final String ITEM_TYPE = "item_type";
        public static final String QTY = "qty";
        public static final String PRICE = "price";
        public static final String DATE_NOW = "date_now";
        public static final String TOTAL_PRICE = "total_price";
    }
}
