package com.example.h.db;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    EditText name , email , psswd , contact;
    Button signup,update,delete,view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.editText);
        email = findViewById(R.id.editText5);
        psswd = findViewById(R.id.editText6);
        contact = findViewById(R.id.editText7);

        signup = findViewById(R.id.button);
        update = findViewById(R.id.button6);
        delete = findViewById(R.id.button5);
        view = findViewById(R.id.button7);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Signup.this , "Signup Successful" , Toast.LENGTH_SHORT).show();
                Intent in = new Intent(Signup.this , Signup.class);
                startActivity(in);

                String name_r = name.getText().toString();
                String email_r = email.getText().toString();
                String psswd_r = psswd.getText().toString();
                String contact_r = contact.getText().toString();

                final DBclass db = new DBclass(Signup.this);
                db.onAdd(name_r , email_r , psswd_r , contact_r);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in= new Intent(Signup.this , Update.class);
                startActivity(in);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in =new Intent(Signup.this , Delete.class);
                startActivity(in);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Signup.this , List.class);
                startActivity(in);
            }
        });
    }
}
