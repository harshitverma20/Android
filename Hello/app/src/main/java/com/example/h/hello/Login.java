package com.example.h.hello;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText email , psswd;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editText5);
        psswd = findViewById(R.id.editText6);
        login = findViewById(R.id.button2);



        SharedPreferences sp = getApplicationContext().getSharedPreferences("My Pref" , MODE_PRIVATE);
         final String email_db = sp.getString("Email" , "");
         final String psswd_db = sp.getString("Password","" );

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(((email.getText().toString()).equals(email_db))&& ((psswd.getText().toString()).equals(psswd_db)))
                {
                    Intent in = new Intent(Login.this , Main2Activity.class);
                    startActivity(in);

                }
                else{
                    Toast.makeText(Login.this , "Invalid user" , Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
