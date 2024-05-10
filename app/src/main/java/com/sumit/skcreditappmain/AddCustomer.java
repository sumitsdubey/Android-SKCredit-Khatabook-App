package com.sumit.skcreditappmain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sumit.skcreditappmain.api.RetrofitClient;
import com.sumit.skcreditappmain.model.AddCustomerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddCustomer extends AppCompatActivity {
    TextInputEditText et_cst_name,et_cst_email, et_cst_mobile, et_cst_address;
    ScrollView theme;
    String user_id;
    AppCompatButton bt_AddCst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
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
        bt_AddCst = (AppCompatButton)findViewById(R.id.bt_AddCst) ;
        et_cst_name = (TextInputEditText) findViewById(R.id.et_cst_name);
        et_cst_email = (TextInputEditText) findViewById(R.id.et_cst_email);
        et_cst_mobile = (TextInputEditText) findViewById(R.id.et_cst_mobile);
        et_cst_address = (TextInputEditText) findViewById(R.id.et_cst_address);



        bt_AddCst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("user_data",MODE_PRIVATE);
                user_id = sp.getString("uid","");
                String cname = et_cst_name.getText().toString().trim();
                String cemail = et_cst_email.getText().toString().trim();
                String cmobile = et_cst_mobile.getText().toString().trim();
                String caddress = et_cst_address.getText().toString().trim();

                if (cname.isEmpty()){
                    et_cst_name.setError("Required");
                    et_cst_name.requestFocus();
                    return;
                }
                if (cemail.isEmpty()){
                    et_cst_email.setError("Required");
                    et_cst_email.requestFocus();
                    return;
                }
                if (cmobile.isEmpty()){
                    et_cst_mobile.setError("Required");
                    et_cst_mobile.requestFocus();
                    return;
                }
                if (caddress.isEmpty()){
                    et_cst_address.setError("Required");
                    et_cst_address.requestFocus();
                    return;
                }

                Call<AddCustomerResponse> call = RetrofitClient.getInstance()
                        .getApi().addcustomer(cname, cemail, cmobile, caddress, user_id);
                call.enqueue(new Callback<AddCustomerResponse>() {
                    @Override
                    public void onResponse(Call<AddCustomerResponse> call, Response<AddCustomerResponse> response) {
                        if (response.isSuccessful()){
                            AddCustomerResponse addCustomerResponse = response.body();
                            if (addCustomerResponse.isStatus()){
                                Toast.makeText(AddCustomer.this, ""+addCustomerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(),Dashboard.class);
                                startActivity(i);
                                finish();
                            }
                            else {
                                Toast.makeText(AddCustomer.this, ""+addCustomerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AddCustomerResponse> call, Throwable t) {
                        Toast.makeText(AddCustomer.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}