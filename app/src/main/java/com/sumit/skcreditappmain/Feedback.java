package com.sumit.skcreditappmain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

public class Feedback extends AppCompatActivity {
    ScrollView theme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().hide();

        theme = (ScrollView)findViewById(R.id.theme);

        SharedPreferences getShared = getSharedPreferences("settheme", MODE_PRIVATE);
        String mytheme_value = getShared.getString("str", "nothing");

        //setting theme

        if (mytheme_value.equals("blue")){
            theme.setBackground(this.getResources().getDrawable(R.drawable.blue_bg));
        }

        if (mytheme_value.equals("nothing")){
            setTheme(R.style.Theme_SkCreditAppMain);
        }
    }
}