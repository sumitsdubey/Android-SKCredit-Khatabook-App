package com.sumit.skcreditappmain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WelcomeScreen extends AppCompatActivity {
    public String color;
    String set_theme;
    ConstraintLayout theme, blur_theme;
    TextView tv_welcome, tv_welcome_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        getSupportActionBar().hide();

        theme = (ConstraintLayout)findViewById(R.id.theme);
        tv_welcome = (TextView)findViewById(R.id.tv_welcome);
        tv_welcome_text = (TextView)findViewById(R.id.tv_welcome_text);
        blur_theme = (ConstraintLayout)findViewById(R.id.blur_theme);

        SharedPreferences sp = getSharedPreferences("user_data",MODE_PRIVATE);
        String spemail = sp.getString("uemail","");
        if( ! spemail.equals("")){
            Intent intent = new Intent(getApplicationContext(),Dashboard.class);
            startActivity(intent);
            finish();
        }

    }


    public void blue(View view) {
        theme.setBackground(this.getResources().getDrawable(R.drawable.blue_bg));
        tv_welcome.setTextColor(Color.parseColor("#FFFFFF"));
        tv_welcome_text.setTextColor(Color.parseColor("#FFFFFF"));
        blur_theme.setBackground(getResources().getDrawable(R.drawable.blur_bg));
        set_theme = "blue";
        gettheme_value();
    }

    public void bt_signup(View view) {
        Intent i = new Intent(WelcomeScreen.this, Register.class);
        startActivity(i);
    }

    public void bt_login(View view) {
        Intent i = new Intent(WelcomeScreen.this, Login.class);
        startActivity(i);
    }
    public void gettheme_value(){
        SharedPreferences shrd = getSharedPreferences("settheme", MODE_PRIVATE);
        SharedPreferences.Editor editor = shrd.edit();
        editor.putString("str",set_theme);
        editor.commit();
    }

    public void light(View view) {
        theme.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tv_welcome.setTextColor(Color.parseColor("#111112"));
        tv_welcome_text.setTextColor(Color.parseColor("#111112"));
        set_theme = "light";
        gettheme_value();
    }
}