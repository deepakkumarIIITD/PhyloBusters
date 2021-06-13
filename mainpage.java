package com.example.projectz20;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class mainpage extends AppCompatActivity {
    private Button selectfile,continuebtn;
    private TextView textplace,fullname,username;
    private Intent intent ;
    public String filepath = "";
    private static final int PERMISSION_REQUEST_STORAGE = 1000;
    private static final int READ_REQUEST_CODE = 42;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        getWindow().setStatusBarColor(getResources().getColor(R.color.yellow));
        String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        selectfile = (Button)findViewById(R.id.selectfile) ;
        continuebtn = (Button)findViewById(R.id.nextpage) ;

        fullname = (TextView)findViewById(R.id.FullName) ;
        username = (TextView)findViewById(R.id.UseRusername) ;
        username.setText(sessionId);
        fullname.setText("DEEPAK KUMAR");
        textplace = (TextView)findViewById(R.id.textplace);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_STORAGE);
        }

        selectfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performfilesearch();
            }
        });
        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainpage.this,load.class);
                intent.putExtra("message_key", filepath);
                startActivity(intent);
                finish();
            }
        });
    }

    private String readText(String input){
        File file = new File(input);
        StringBuilder text = new StringBuilder();
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                text.append(line);
                text.append("\n");
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    private void performfilesearch(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                String path = uri.getPath();
                path = path.substring(path.indexOf(":") + 1);
                Toast.makeText(this, "" + path, Toast.LENGTH_SHORT).show();
                textplace.setText(path);
                filepath = path;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if(requestCode == PERMISSION_REQUEST_STORAGE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
//              permission granted
                Toast.makeText(this,"quickcole@@1",Toast.LENGTH_SHORT).show();
            }else{
//              permission not granted
                Toast.makeText(this,"quackcole##1",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}