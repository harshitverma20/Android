package com.example.h.hello;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView name , psswd , email , phno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name  = findViewById(R.id.textView14);
        psswd = findViewById(R.id.textView15);
        email = findViewById(R.id.textView16);
        phno = findViewById(R.id.textView17);

        SharedPreferences sp = getApplication().getSharedPreferences("My Pref" , MODE_PRIVATE);

        String name_f = sp.getString("Name" , "");
        String psswd_f = sp.getString("Password"  , "");
        String email_f = sp.getString("Email" , "");
        String phno_f = sp.getString("Contact" , "");

        name.setText(name_f);
        psswd.setText(psswd_f);
        email.setText(email_f);
        phno.setText(phno_f);
    }
}
