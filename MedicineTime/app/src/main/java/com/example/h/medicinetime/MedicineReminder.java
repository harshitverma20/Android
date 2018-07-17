package com.example.h.medicinetime;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MedicineReminder extends AppCompatActivity {
    FloatingActionButton fab;
    ListView list;
    SQLiteDatabase database;
    DBclass db;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_reminder);

        list=findViewById(R.id.listview);
        fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                    //floating action button(fab)
                Intent in = new Intent(MedicineReminder.this , Change.class);
                startActivity(in);
            }
        });

        final ArrayList<String> aL = new ArrayList<>();                     //array list
        db = new DBclass(MedicineReminder.this);
        Cursor cursor = db.getData();
        database = db.getWritableDatabase();

        if(cursor.moveToFirst()){                                       //adding items to list view
            do{
                String medname = cursor.getString(cursor.getColumnIndex("MedName"));
                String date = cursor.getString(cursor.getColumnIndex("Date"));
                String time = cursor.getString(cursor.getColumnIndex("Time"));

                aL.add(" Medicine Name: "+medname+"\n Date: "+date+", Time: "+time);
            }while(cursor.moveToNext());
        }
        final ArrayAdapter ad = new ArrayAdapter(MedicineReminder.this , android.R.layout.simple_list_item_1,aL);
        list.setAdapter(ad);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {                 //long hold to delete
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                DBclass db = new DBclass(MedicineReminder.this);
                Cursor cursor = db.getData();
                cursor.moveToPosition(position);
                String medname = cursor.getString(cursor.getColumnIndex("MedName"));

                db.onDelete(medname);

                aL.remove(position);
                ad.notifyDataSetChanged();

                Toast.makeText(MedicineReminder.this , "reminder deleted" , Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
