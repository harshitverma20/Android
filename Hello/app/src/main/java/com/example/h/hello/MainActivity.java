package com.example.h.hello;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name , email , psswd , phno;
    Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editText);
        email = findViewById(R.id.editText2);
        psswd = findViewById(R.id.editText3);
        phno = findViewById(R.id.editText4);

        b = findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getApplicationContext().getSharedPreferences("My Pref" , MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("Name " ,name.getText().toString() );
                editor.putString("Email" , email.getText().toString());
                editor.putString("Password" , psswd.getText().toString());
                editor.putString("Contact" , phno.getText().toString());
                editor.commit();


                Toast.makeText(MainActivity.this , "Login Successful!" , Toast.LENGTH_SHORT).show();

                Intent in = new Intent(MainActivity.this , Login.class);
                startActivity(in);

            }
        });
    }
}
