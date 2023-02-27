package com.example.sqlitedemo_b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseOperations extends SQLiteOpenHelper {
    public DatabaseOperations(@Nullable Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME,
                null, 1);
    }
    public String CREATE_QUERY = "Create table "
            + TableData.TableInfo.TABLE_NAME
            +" ("+ TableData.TableInfo.ID+" TEXT, "
            + TableData.TableInfo.NAME+" TEXT, "
            + TableData.TableInfo.SURNAME+" TEXT,"
            + TableData.TableInfo.MARKS+" TEXT);";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);
    }
    public void putInformation(DatabaseOperations dop,
      String name, String surname, String id, String marks){

        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TableData.TableInfo.ID, id);
        cv.put(TableData.TableInfo.NAME, name);
        cv.put(TableData.TableInfo.SURNAME, surname);
        cv.put(TableData.TableInfo.MARKS, marks);

        long k = SQ.insert(TableData.TableInfo.TABLE_NAME,
                null, cv);
    }

    public void deleteInfo(DatabaseOperations dop, String id){
        SQLiteDatabase SQ = dop.getWritableDatabase();
        String arg[] = {id};
        String selection = TableData.TableInfo.ID+" LIKE ?";
        SQ.delete(TableData.TableInfo.TABLE_NAME, selection, arg);
    }
    public Cursor getInfo(DatabaseOperations dop, String id){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String selection = TableData.TableInfo.ID+" LIKE ?";
        String columns[] = {TableData.TableInfo.ID,
                TableData.TableInfo.NAME, TableData.TableInfo.SURNAME,
                TableData.TableInfo.MARKS};
        String arg[] = {id};

        Cursor CR = SQ.query(TableData.TableInfo.TABLE_NAME,  columns,
                selection, arg, null, null, null);
        return CR;
    }

    public void UpdateInfo(DatabaseOperations dop,
                           String new_marks, String id){
        SQLiteDatabase SQ = dop.getWritableDatabase();
        String selection = TableData.TableInfo.ID+" LIKE ?";
        String arg[] = {id};
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.MARKS, new_marks);
        SQ.update(TableData.TableInfo.TABLE_NAME, cv, selection, arg);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
