package com.sumit.skcreditappmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sumit.skcreditappmain.api.RetrofitClient;
import com.sumit.skcreditappmain.model.CreateUserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {


    TextInputEditText et_name, et_email, et_mobile, et_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
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

        //find id
        et_name = (TextInputEditText) findViewById(R.id.et_name);
        et_email = (TextInputEditText) findViewById(R.id.et_email);
        et_mobile = (TextInputEditText) findViewById(R.id.et_mobile);
        et_password = (TextInputEditText) findViewById(R.id.et_password);


    }

    public void signin(View view) {
        Intent i = new Intent(Register.this, Login.class);
        startActivity(i);
        finish();
    }

    public void Getlogin(View view) {

        String name = et_name.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String mobile = et_mobile.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        //validation in textbox
        if (name.isEmpty()){
            et_name.setError("Required");
            et_name.requestFocus();
            return;
        }
        if (email.isEmpty()){
            et_email.setError("Required");
            et_email.requestFocus();
            return;
        }
        if (mobile.isEmpty()){
            et_mobile.setError("Required");
            et_mobile.requestFocus();
            return;
        }
        if (password.isEmpty()){
            et_password.setError("Required");
            et_password.requestFocus();
            return;
        }

        Call<CreateUserResponse> call = RetrofitClient.getInstance().getApi().createUser(name,email,mobile,password);
        call.enqueue(new Callback<CreateUserResponse>() {
            @Override
            public void onResponse(Call<CreateUserResponse> call, Response<CreateUserResponse> response) {
                if (response.isSuccessful()){
                    CreateUserResponse userResponse = response.body();
                    Toast.makeText(Register.this, ""+userResponse.message, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),Login.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<CreateUserResponse> call, Throwable t) {
                Toast.makeText(Register.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}