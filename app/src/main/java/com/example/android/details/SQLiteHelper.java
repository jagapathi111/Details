package com.example.android.details;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import org.w3c.dom.Text;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String name, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO FOOD VALUES (NULL, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindBlob(2, image);

        statement.executeInsert();
    }

    public void insertBreakfast(String name, String  date) {

        SQLiteDatabase database = getWritableDatabase();
         String sql = "INSERT INTO BREAKFAST VALUES (NULL, ?, ?)";

         SQLiteStatement statement = database.compileStatement(sql);
         statement.clearBindings();

         statement.bindString(1, name);
         statement.bindString(2,date );

         statement.executeInsert();

    }

    public Cursor getBreakfast() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor b = database.rawQuery("select * from  BREAKFAST", null);
         return b;


    }


    public void insertData1(String name, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO FOOD1 VALUES (NULL, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindBlob(2, image);

        statement.executeInsert();
    }

    public void insertLunch(String name, String  date) {

        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO LUNCH VALUES (NULL, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2,date );

        statement.executeInsert();

    }

    public Cursor getLunch() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor b = database.rawQuery("select * from  LUNCH", null);
        return b;


    }


    public void insertData2(String name, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO FOOD2 VALUES (NULL, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindBlob(2, image);

        statement.executeInsert();
    }

    public void insertDinner(String name, String  date) {

        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO DINNER VALUES (NULL, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2,date );

        statement.executeInsert();

    }

    public Cursor getDinner() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor b = database.rawQuery("select * from  DINNER", null);
        return b;


    }



    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
