package com.example.h.note_making_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class EditNotes extends AppCompatActivity {

    EditText title_edit , content_edit;
    ImageButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        title_edit = findViewById(R.id.editText3);

        content_edit = findViewById(R.id.editText4);
        content_edit.setSingleLine(false);

        btn = findViewById(R.id.imageButton3);

        String title = getIntent().getStringExtra("Title_old");
        String content = getIntent().getStringExtra("Content_old");

        final String title_old = getIntent().getStringExtra("Title_old");
        //final String content_old = content;

        title_edit.setText(title);
        content_edit.setText(content);

        final String title_new = title_edit.getText().toString();
        final String content_new = content_edit.getText().toString();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBclass db = new DBclass(getApplicationContext());

                db.onUpdate(title_new , content_new , title_old);


                Toast.makeText(getApplicationContext() , "Saved Successful" ,Toast.LENGTH_SHORT ).show();

                Intent in =new Intent(getApplicationContext() , MainActivity.class);
                startActivity(in);
            }
        });

    }
}
