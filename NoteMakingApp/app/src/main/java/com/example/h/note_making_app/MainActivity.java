package com.example.h.note_making_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.app.ListActivity;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_add;
    ListView list;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        btn_add = findViewById(R.id.imageButton4);
        list = findViewById(R.id.listView);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this , AddNotes.class);
                startActivity(in);
            }
        });

        final ArrayList<String> aL = new ArrayList<>();

        DBclass db = new DBclass(getApplicationContext());
        Cursor cursor = db.getData();
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();

        if(cursor.moveToFirst()){
            do{
                String title = cursor.getString(cursor.getColumnIndex("Title"));
                String content = cursor.getString(cursor.getColumnIndex("Content"));

                aL.add(" "+title+"\n "+content);
            }while(cursor.moveToNext());
        }

        final ArrayAdapter ad = new ArrayAdapter(getApplicationContext() , android.R.layout.simple_list_item_multiple_choice , aL);
        list.setAdapter(ad);
    //    SparseBooleanArray checked = list.getCheckedItemPositions();


        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Warning!");
                alert.setMessage("Do you want to delete record?");

                final int pos = position;

                alert.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        aL.remove(pos);

                        DBclass db = new DBclass(getApplicationContext());
                        Cursor cursor = db.getData();
                        cursor.moveToPosition(pos);

                        String title = cursor.getString(cursor.getColumnIndex("Title"));
                        db.onDelete(title);

                        ad.notifyDataSetChanged();

                        Toast.makeText(getApplicationContext() , "Deleted" , Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });
                alert.show();
                return true;
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                DBclass db = new DBclass(getApplicationContext());
                Cursor cursor = db.getData();
                cursor.moveToPosition(position);

                String title = cursor.getString(cursor.getColumnIndex("Title"));
                String content = cursor.getString(cursor.getColumnIndex("Content"));

                Intent in = new Intent(getApplicationContext() , EditNotes.class);
                in.putExtra("Title_old",title);
                in.putExtra("Content_old" , content);
                startActivity(in);
            }
        });
    }
}
