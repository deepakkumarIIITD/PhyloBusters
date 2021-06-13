package com.example.projectz20;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import java.util.Random;

public class load extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        int[] array = {11500,13500,12500,13000,12000,11000,10000};
        Intent i = getIntent();
        String result = i.getStringExtra("message_key");
        int rnd = new Random().nextInt(array.length);
        int time = array[rnd];
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run(){
                Intent intent = new Intent(load.this,InfoPage.class);
                if(result.contains("seq1")){
                    intent.putExtra("filenum", "1");
                }else if(result.contains("seq2")){
                    intent.putExtra("filenum", "2");
                }
                startActivity(intent);
                finish();
            }
        },time);
    }
}