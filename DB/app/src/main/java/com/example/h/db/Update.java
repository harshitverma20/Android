package com.example.h.db;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {

    EditText email,old_p , new_p;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        email = findViewById(R.id.editText4);
        old_p = findViewById(R.id.editText8);
        new_p = findViewById(R.id.editText9);
        b = findViewById(R.id.button3);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBclass db = new DBclass(Update.this);
                db.onUpdate(email.getText().toString() , old_p.getText().toString() , new_p.getText().toString());

                Intent in = new Intent(Update.this , Signup.class);
                startActivity(in);
                Toast.makeText(Update.this , "update successful" , Toast.LENGTH_LONG).show();
            }
        });
    }
}