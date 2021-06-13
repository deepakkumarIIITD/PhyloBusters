package com.example.projectz20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zolad.zoominimageview.ZoomInImageView;

public class InfoPage extends AppCompatActivity {
    ZoomInImageView TREEVIEW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        Button openseqbtn = findViewById(R.id.os);
        TREEVIEW = findViewById(R.id.treeimage);
        Intent i = getIntent();
        String result = i.getStringExtra("filenum");
        if(result.equals("1")){
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.tree1);
            TREEVIEW.setImageBitmap(bitmap);
        }else if(result.equals("2")){
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.tree2);
            TREEVIEW.setImageBitmap(bitmap);
        }
        Button opendistancematrix = findViewById(R.id.odm);
        Button opensequences = findViewById(R.id.os);
        Button openalignedsequences = findViewById(R.id.oas);

        opendistancematrix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result.equals("1")){
                    Intent intent = new Intent(InfoPage.this,pdfviewer.class);
                    intent.putExtra("whichfile", "1odm");
                    startActivity(intent);
                    finish();
                }else if(result.equals("2")){
                    Intent intent = new Intent(InfoPage.this,pdfviewer.class);
                    intent.putExtra("whichfile", "2odm");
                    startActivity(intent);
                    finish();
                }
            }
        });

        opensequences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result.equals("1")){
                    Intent intent = new Intent(InfoPage.this,pdfviewer.class);
                    intent.putExtra("whichfile", "1os");
                    startActivity(intent);
                    finish();
                }else if(result.equals("2")){
                    Intent intent = new Intent(InfoPage.this,pdfviewer.class);
                    intent.putExtra("whichfile", "2os");
                    startActivity(intent);
                    finish();
                }
            }
        });

        openalignedsequences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result.equals("1")){
                    Intent intent = new Intent(InfoPage.this,pdfviewer.class);
                    intent.putExtra("whichfile", "1oas");
                    startActivity(intent);
                    finish();
                }else if(result.equals("2")){
                    Intent intent = new Intent(InfoPage.this,pdfviewer.class);
                    intent.putExtra("whichfile", "2oas");
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}