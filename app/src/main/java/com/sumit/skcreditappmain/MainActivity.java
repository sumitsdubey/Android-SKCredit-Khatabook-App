package com.sumit.skcreditappmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Animation logo_anim, slogan_anim;
    ImageView iv_logo;
    TextView tv_slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //Animation
        logo_anim = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        slogan_anim = AnimationUtils.loadAnimation(this, R.anim.slogan_animation);


        iv_logo = findViewById(R.id.iv_logo);
        iv_logo.setAnimation(logo_anim);

        tv_slogan = findViewById(R.id.tv_slogan);
        tv_slogan.setAnimation(slogan_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, WelcomeScreen.class);
                startActivity(i);
                finish();
            }
        } ,2500);
    }
}