package com.example.sagar.lab5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhandler extends SQLiteOpenHelper {
    public DBhandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "login.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table login(id integer primary key autoincrement,uname varchar(20),pass varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    public void insertData(String uname, String pass){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues v=new ContentValues();
        v.put("uname",uname);
        v.put("pass",pass);
        db.insertOrThrow("login",null,v);
        db.close();
    }

    public boolean compare(String uname,String pass){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select * from login where uname='"+uname+"' and pass='"+pass+"'",null);
        c.moveToFirst();
        Boolean t=false;
        if(c.getCount()>0) {
            t=true;
        }
        return t;
    }


}
