package com.example.h.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    EditText name , email , psswd , phone;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.editText);
        email = findViewById(R.id.editText2);
        psswd = findViewById(R.id.editText3);
        phone = findViewById(R.id.editText4);

        signup = findViewById(R.id.button);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Signup.this , Login.class);
                startActivity(in);
                Toast.makeText(Signup.this,"login successful" , Toast.LENGTH_SHORT).show();

            }
        });
    }
}
