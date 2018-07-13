package com.example.h.db;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lv = findViewById(R.id.listview);

        ArrayList<String> aL = new ArrayList<>();
        DBclass db = new DBclass(List.this);
        Cursor cursor = db.getData();

        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndex("Name"));
                String email = cursor.getString(cursor.getColumnIndex("Email"));
                String psswd = cursor.getString(cursor.getColumnIndex("Password"));
                String contact = cursor.getString(cursor.getColumnIndex("Contact"));

                aL.add(name+" "+email+" "+psswd+" "+contact);

            }while(cursor.moveToNext());

            ArrayAdapter ad = new ArrayAdapter(List.this , android.R.layout.simple_list_item_1 , aL);
            lv.setAdapter(ad);
        }
    }

}
