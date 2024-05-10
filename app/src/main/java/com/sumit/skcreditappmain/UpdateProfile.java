package com.sumit.skcreditappmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ScrollView;

import com.google.android.material.textfield.TextInputEditText;

public class UpdateProfile extends AppCompatActivity {
    ScrollView theme;
    TextInputEditText et_name,et_mobile, et_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        getSupportActionBar().hide();

        et_name = (TextInputEditText)findViewById(R.id.et_name);
        et_email = (TextInputEditText)findViewById(R.id.et_email);
        et_mobile = (TextInputEditText)findViewById(R.id.et_mobile);

        ScrollView theme = (ScrollView) findViewById(R.id.theme);

        SharedPreferences getShared = getSharedPreferences("settheme", MODE_PRIVATE);
        String mytheme_value = getShared.getString("str", "nothing");

        //setting theme

        if (mytheme_value.equals("blue")) {
            theme.setBackground(this.getResources().getDrawable(R.drawable.blue_bg));
        }


        if (mytheme_value.equals("nothing")) {
            setTheme(R.style.Theme_SkCreditAppMain);
        }
        SharedPreferences sp = getSharedPreferences("user_data",MODE_PRIVATE);
        String name = sp.getString("uname","");
        String mobile = sp.getString("umobile","");
        String email = sp.getString("uemail","");

        et_name.setText(name);
        et_email.setText(email);
        et_mobile.setText(mobile);
    }
}