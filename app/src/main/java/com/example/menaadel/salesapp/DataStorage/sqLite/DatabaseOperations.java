package com.example.menaadel.salesapp.DataStorage.sqLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.menaadel.salesapp.Utils.UtilsFunctions;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static com.example.menaadel.salesapp.DataStorage.sqLite.TableData.TableInfo.DATE_NOW;


/**
 * Created by MenaAdel on 1/17/2018.
 */

public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int DatabaseVersion=1;
    public String Query = "create table if not exists "+ TableData.TableInfo.TABLE_NAME + "("
            + TableData.TableInfo.ITEM_TYPE + " text,"
            + TableData.TableInfo.QTY + " text,"
            + TableData.TableInfo.PRICE + " text,"
            + TableData.TableInfo.TOTAL_PRICE + " text,"
            + DATE_NOW + " text);";

    public DatabaseOperations(Context context){
        super(context, TableData.TableInfo.DATABASE_NAME, null, DatabaseVersion);
        Log.d("Database Operation>>>>","Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Query);
        Log.d("Database Operation>>>>","Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /*
    *Insert new row
     */
    public void INSERT(String itemType ,String qty ,String price ,String totalPrice){
        SQLiteDatabase sq=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.ITEM_TYPE,itemType);
        cv.put(TableData.TableInfo.QTY,qty);
        cv.put(TableData.TableInfo.PRICE,price);
        cv.put(TableData.TableInfo.TOTAL_PRICE ,totalPrice);
        cv.put(DATE_NOW , UtilsFunctions.getCurrentDate(new SimpleDateFormat("MMM d, yyyy", Locale.US)));
        long k=sq.insert(TableData.TableInfo.TABLE_NAME,null,cv);
        Log.d("Database operation>>>>","One row inserted");
    }

    /*
    * select from sqlite
     */
    public Cursor SELECT(DatabaseOperations dbop){
        String query="SELECT * FROM "+ TableData.TableInfo.TABLE_NAME+";";
        SQLiteDatabase sq=dbop.getReadableDatabase();
        Cursor cr=sq.rawQuery(query,null);
        if(cr!=null)
            cr.moveToFirst();
        return cr;
    }
}
