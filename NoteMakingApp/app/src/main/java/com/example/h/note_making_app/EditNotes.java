package com.example.h.note_making_app;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class EditNotes extends AppCompatActivity {

    EditText title_edit , content_edit;
    Button btn_save , btn_del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        title_edit = findViewById(R.id.editText3);

        content_edit = findViewById(R.id.editText4);
        content_edit.setSingleLine(false);

        btn_save = findViewById(R.id.button);
        btn_del = findViewById(R.id.button2);

        String title = getIntent().getStringExtra("Title_old");
        String content = getIntent().getStringExtra("Content_old");

        final String title_old = getIntent().getStringExtra("Title_old");
    //    final String content_old = getIntent().getStringExtra("Content_old");

        title_edit.setText(title);
        content_edit.setText(content);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBclass db = new DBclass(getApplicationContext());

                db.onUpdate(title_edit.getText().toString().trim() , content_edit.getText().toString().trim() , title_old);


                Toast.makeText(getApplicationContext() , "Saved Successful" ,Toast.LENGTH_SHORT ).show();

                Intent in =new Intent(getApplicationContext() , MainActivity.class);
                startActivity(in);
            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBclass db = new DBclass(getApplicationContext());
                Cursor cursor = db.getData();

                db.onDelete(title_old);

                Toast.makeText(getApplicationContext() , "Deleted" , Toast.LENGTH_SHORT).show();

                Intent in = new Intent(getApplicationContext() , MainActivity.class);
                startActivity(in);
                finish();
            }
        });

    }
}
