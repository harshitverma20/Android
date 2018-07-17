package com.example.h.medicinetime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBclass extends SQLiteOpenHelper {
    public DBclass(Context context)
    {
        super(context, "MedDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE MEDICINE (MedName , Date , Time)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS MEDICINE");
        onCreate(sqLiteDatabase);
    }

    public void onAdd(String medname , String date , String time){
        ContentValues cv = new ContentValues();
        cv.put("MedName" , medname);
        cv.put("Date" , date);
        cv.put("Time" , time);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert("MEDICINE" , null , cv);
    }

    public Cursor getData(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.query("MEDICINE" ,null,null,null,null,null,null);
        return  cursor;
    }

 /*   void onUpdate(String medname , String date , String time){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();

    }*/

    public void onDelete(String medname){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String arr[] = {medname};

        sqLiteDatabase.delete("MEDICINE" , "MedName=?" , arr);
    }
}
