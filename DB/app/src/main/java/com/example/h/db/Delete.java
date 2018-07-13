package com.example.h.db;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete extends AppCompatActivity {

    EditText email;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        email=findViewById(R.id.editText10);
        delete=findViewById(R.id.button4);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBclass db = new DBclass(Delete.this);
                db.onDelete(email.getText().toString());

                Intent in = new Intent(Delete.this , Signup.class);
                startActivity(in);

                Toast.makeText(Delete.this , "deletion successful" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
