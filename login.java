package com.example.projectz20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class login extends AppCompatActivity {
    Button callSignUp,continuebut;
    EditText username,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
//        hooks
        callSignUp = findViewById(R.id.newuser);
        username = findViewById(R.id.Username);
        String usernamedata = (username.getText()).toString();
        pass = findViewById(R.id.password);
        continuebut = findViewById(R.id.continuebtn);

        continuebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,mainpage.class);
                intent.putExtra("EXTRA_SESSION_ID", usernamedata);
                startActivity(intent);
                finish();
            }
        });


        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,regestration.class);
                startActivity(intent);
                finish();
            }
        });

    }
}