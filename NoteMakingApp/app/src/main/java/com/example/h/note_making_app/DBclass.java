package com.example.h.note_making_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBclass extends SQLiteOpenHelper {
    public DBclass(Context context) {
        super(context, "NotesDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE NOTES (Title , Content)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NOTES");
        onCreate(sqLiteDatabase);
    }

    public void onAdd(String title , String content){
        ContentValues cv = new ContentValues();
        cv.put("Title" , title);
        cv.put("Content" , content);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert("NOTES" , null , cv);
    }

    public Cursor getData(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.query("NOTES" , null,null,null,null,null,null,null);
        return cursor;
    }

    public void onDelete(String title){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String arr[] = {title};

        sqLiteDatabase.delete("NOTES" , "Title=?",arr);
    }

    public void onUpdate(String title_new, String content_new , String title_old){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Title" , title_new);
        cv.put("Content" , content_new);

        String arr[]={title_old};

        sqLiteDatabase.update("NOTES",cv,"Title=?",arr);
    }
}
