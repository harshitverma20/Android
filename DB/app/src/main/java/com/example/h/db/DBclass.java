package com.example.h.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBclass extends SQLiteOpenHelper {
    public DBclass(Context context) {
        super(context, "USERDB3", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE STUDENT (Name text , Email text , Password text , Contact text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS STUDENT");
    }

    public void onAdd(String name_r , String email_r , String psswd_r , String contact_r){
        ContentValues cv = new ContentValues();
        cv.put("Name" ,name_r);
        cv.put("Email" ,email_r);
        cv.put("Password" ,psswd_r);
        cv.put("Contact" ,contact_r);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert("STUDENT" ,null , cv );
    }

    public Cursor getData(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.query("STUDENT",null,null,null,null,null,null);
        return cursor;
    }

    void onUpdate(String email , String psswd_old , String psswd_new){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Email" , email);
        cv.put("Password", psswd_new);

        String arr[] = {email,psswd_old};

        sqLiteDatabase.update("STUDENT",cv,"Email = ? AND Password = ?" , arr);
    }

    void onDelete(String email){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String arr[] = {email};
        sqLiteDatabase.delete("STUDENT" , "Email = ?" , arr);
    }
}