package com.example.h.note_making_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AddNotes extends AppCompatActivity {

    EditText title , content;
    Button btn_save, btn_cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        title = findViewById(R.id.editText3);

        content = findViewById(R.id.editText2);
        content.setSingleLine(false);

        btn_save = findViewById(R.id.button3);
        btn_cancel = findViewById(R.id.button4);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBclass db = new DBclass(getApplicationContext());
                db.onAdd(title.getText().toString().trim() , content.getText().toString().trim());

                Toast.makeText(AddNotes.this , "Saved Successful" , Toast.LENGTH_SHORT).show();

                Intent in = new Intent(getApplicationContext() , MainActivity.class);
                startActivity(in);
                finish();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();

                Intent in = new Intent(getApplicationContext() , MainActivity.class);
                startActivity(in);
                finish();
            }
        });

    }
}
