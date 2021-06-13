package com.example.projectz20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

public class pdfviewer extends AppCompatActivity {

    PDFView mypdfview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        Intent i = getIntent();
        String result = i.getStringExtra("whichfile");
        String returnval = String.valueOf((result.toString().charAt(0)));
        Button back_button = findViewById(R.id.backbutton);
        mypdfview = (PDFView) findViewById(R.id.pdfView);
        if(result.equals("1odm")){
            mypdfview.fromAsset("dmseq1.pdf").load();
        }else if(result.equals("1os")){
            mypdfview.fromAsset("seq1.pdf").load();
        }else if(result.equals("1oas")){
            mypdfview.fromAsset("seq1alignment.pdf").load();
        }else if(result.equals("2odm")){
            mypdfview.fromAsset("dmseq2.pdf").load();
        }else if(result.equals("2os")){
            mypdfview.fromAsset("seq2.pdf").load();
        }else if(result.equals("2oas")){
            mypdfview.fromAsset("seq2alignment.pdf").load();
        }
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pdfviewer.this,InfoPage.class);
                intent.putExtra("filenum", returnval);
                startActivity(intent);
                finish();
            }
        });
    }
}