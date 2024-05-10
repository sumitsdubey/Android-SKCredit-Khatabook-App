package com.sumit.skcreditappmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sumit.skcreditappmain.api.RetrofitClient;
import com.sumit.skcreditappmain.model.User;
import com.sumit.skcreditappmain.model.UserLoginResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {
    private Button btn_login;
    private LinearLayout theme;
    private EditText et_username, et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();


        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);
        theme = (LinearLayout)findViewById(R.id.theme);



        btn_login = (Button)findViewById(R.id.btn_login);
        SharedPreferences getShared = getSharedPreferences("settheme", MODE_PRIVATE);
        String mytheme_value = getShared.getString("str", "nothing");

        //setting theme

        if (mytheme_value.equals("blue")){
            theme.setBackground(this.getResources().getDrawable(R.drawable.blue_bg));
        }

        if (mytheme_value.equals("nothing")){
            setTheme(R.style.Theme_SkCreditAppMain);
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = et_username.getText().toString();
                String pass = et_password.getText().toString();

                //set validation on textbox
                if (user.isEmpty()){
                    et_username.setError("Required");
                    et_username.requestFocus();
                    return;
                }
                if (pass.isEmpty()){
                    et_password.setError("Required");
                    et_password.requestFocus();
                    return;
                }

                Call<UserLoginResponse> call = RetrofitClient.getInstance().getApi().login(user,pass);
                call.enqueue(new Callback<UserLoginResponse>() {
                    @Override
                    public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                        if (response.isSuccessful()){
                            UserLoginResponse userLoginResponse = response.body();
                            if (userLoginResponse.isStatus()) {
                                Toast.makeText(Login.this, "" + userLoginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                //getting data
                                ArrayList<User> users = userLoginResponse.getData();

                                //shared prefference save data
                                SharedPreferences shrd = getSharedPreferences("user_data", MODE_PRIVATE);
                                SharedPreferences.Editor editor = shrd.edit();
                                editor.putString("uid",users.get(0).getId());
                                editor.putString("uname",users.get(0).getName());
                                editor.putString("uemail",users.get(0).getEmail());
                                editor.putString("upassword",users.get(0).getPassword());
                                editor.putString("umobile",users.get(0).getMobile());
                                editor.putString("udate_time",users.get(0).getDate_time());
                                editor.putString("uis_login",users.get(0).getIs_login());
                                editor.putString("ustatus",users.get(0).getStatus());
                                editor.putString("utoken",users.get(0).getToken());
                                editor.commit();
                                //after login succesfull get intent in Dashboard
                                Intent i = new Intent(getApplicationContext(), Dashboard.class);
                                startActivity(i);
                                finish();
                            }
                            else{
                                Toast.makeText(Login.this, ""+userLoginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                    Toast.makeText(Login.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
//                if (user.equals("admin") && pass.equals("admin")){
//
//                    //shared preferences
//                    SharedPreferences shrd = getSharedPreferences("user_data", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = shrd.edit();
//                    editor.putString("username",user);
//                    editor.putString("password",pass);
//                    editor.commit();
//
//                    Intent i = new Intent(Login.this, Dashboard.class);
//                    startActivity(i);
//                    finish();
//                }
//                else{
//                    Toast.makeText(Login.this, "Incorrect User And Password", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    public void signup(View view) {
        Intent i = new Intent(Login.this, Register.class);
        startActivity(i);
        finish();
    }

}