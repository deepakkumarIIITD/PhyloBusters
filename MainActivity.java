package com.example.projectz20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    Animation topanim, bottomanim;
    ImageView image;
    TextView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        animations
        topanim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
//         HOOKS
        image = findViewById(R.id.Image);
        logo = findViewById(R.id.Logo);
        image.setAnimation(topanim);
        logo.setAnimation(bottomanim);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run(){
                Intent intent = new Intent(MainActivity.this,login.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}