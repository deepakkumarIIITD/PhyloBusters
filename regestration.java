package com.example.projectz20;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class regestration extends AppCompatActivity {
    EditText regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn;
    FirebaseDatabase rootNode;
    DatabaseReference refrence;
    helperclass helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestration);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
//        hooks
        regName = findViewById(R.id.FullName);
        regUsername = findViewById(R.id.Username);
        regEmail = findViewById(R.id.email);
        regPhoneNo = findViewById(R.id.Phoneno);
        regPassword = findViewById(R.id.password);
        regBtn = findViewById(R.id.regestration);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean proceedornot = registeruser();
                if(proceedornot){
                    rootNode = FirebaseDatabase.getInstance();
                    refrence = rootNode.getReference("login");
                    refrence.child(helper.getUsername()+helper.getDatetime()).setValue("logged in");
                    Intent intent = new Intent(regestration.this,mainpage.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private Boolean validateName(){
        String val = regName.getText().toString();
        if(val.isEmpty()){
            regName.setError("Field cannot be empty");
            return false;
        }else{
            regName.setError(null);
            return true;
        }
    }

    private Boolean validateusernameName(){
        String val = regUsername.getText().toString();
        if(val.isEmpty()){
            regUsername.setError("Field cannot be empty");
            return false;
        }else if(val.length() > 20){
            regUsername.setError("Username at max can be of 20 characters");
            return false;
        }else{
            regUsername.setError(null);
            return true;
        }
    }

    private Boolean validateEmail(){
        String val = regEmail.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(val.isEmpty()){
            regEmail.setError("Field cannot be empty");
            return false;
        }else if(!val.matches(emailPattern)){
            regEmail.setError("Incorrect pattern for email (---@--.---)");
            return false;
        }else{
            regEmail.setError(null);
            return true;
        }
    }

    private Boolean validatephoneno(){
        String val = regPhoneNo.getText().toString();
        if(val.isEmpty()){
            regPhoneNo.setError("Field cannot be empty");
            return false;
        }else{
            regPhoneNo.setError(null);
            return true;
        }
    }

    private Boolean validatepassword(){
        String val = regPassword.getText().toString();
        String passwordVal = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        if(val.isEmpty()){
            regPassword.setError("Field cannot be empty");
            return false;
        }else if(!val.matches(passwordVal)){
            regPassword.setError("There should be atlease:\n1) one digit\n2)one uppercase letter\n3)one Lowercase letter\n4)no whitespaces\n5)size in range 8 to 20");
            return false;
        }else{
            regPassword.setError(null);
            return true;
        }
    }

    public boolean registeruser(){

        if(!validateName() | !validateEmail() | !validatepassword() | !validatephoneno() | !validateusernameName()){
            return false;
        }

        rootNode = FirebaseDatabase.getInstance();
        refrence = rootNode.getReference("registeration");
        String name = regName.getText().toString();
        String username = regUsername.getText().toString();
        String email = regEmail.getText().toString();
        String phone = regPhoneNo.getText().toString();
        String password = regPassword.getText().toString();
        helper = new helperclass(name,username,email,phone,password);
        refrence.child(username+helper.getDatetime()).setValue(helper);
        return true;
    }
}