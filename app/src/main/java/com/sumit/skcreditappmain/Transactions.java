package com.sumit.skcreditappmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sumit.skcreditappmain.api.RetrofitClient;
import com.sumit.skcreditappmain.model.AddTransactionResponse;
import com.sumit.skcreditappmain.model.Customer;
import com.sumit.skcreditappmain.model.CustomerResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Transactions extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ScrollView theme;
    private TextInputEditText et_trans_title,et_trans_amount, et_trans_description;
    private int user_id;
    private Spinner sp_cstname;
    ArrayAdapter<Customer> ad;
    private int customer_id;
    private RadioGroup rg_amount;
    private String amount_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
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

        et_trans_title = (TextInputEditText) findViewById(R.id.et_trans_title);
        et_trans_amount = (TextInputEditText) findViewById(R.id.et_trans_amount);
        et_trans_description = (TextInputEditText) findViewById(R.id.et_trans_description);
        sp_cstname = (Spinner) findViewById(R.id.sp_cstname);
        rg_amount = (RadioGroup)findViewById(R.id.rg_amount);

        getCustomer();
    }

    private void getCustomer() {
            SharedPreferences sp = getSharedPreferences("user_data",MODE_PRIVATE);
            user_id = Integer.parseInt(sp.getString("uid",""));
        Toast.makeText(this, ""+user_id, Toast.LENGTH_SHORT).show();
            //calling response for Spinner of Customers
        Call<CustomerResponse> call = RetrofitClient.getInstance().getApi().getcustomer(user_id);
        call.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if (response.isSuccessful()){
                    CustomerResponse getCustomerResponse = response.body();
                    if (getCustomerResponse.isStatus()){
                        ArrayList<Customer> customers = getCustomerResponse.getData();
                        ad= new ArrayAdapter<Customer>(Transactions.this, android.R.layout.simple_spinner_dropdown_item,customers);
                        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_cstname.setAdapter(ad);
                        sp_cstname.setOnItemSelectedListener(Transactions.this);
                    }else{
                        Toast.makeText(Transactions.this, ""+getCustomerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
            Toast.makeText(Transactions.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    customer_id = ad.getItem(i).getId();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void setType(View view) {
        int radioId = rg_amount.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton)findViewById(radioId);
        amount_type = radioButton.getText().toString().trim();
        Toast.makeText(this, ""+amount_type, Toast.LENGTH_SHORT).show();
    }

    public void addTransaction(View view) {
        String title = et_trans_title.getText().toString().trim();
        String amt = et_trans_amount.getText().toString().trim();
        String description = et_trans_description.getText().toString().trim();

        Call<AddTransactionResponse> call = RetrofitClient.getInstance().getApi().addTransaction(user_id,customer_id,title,amt,amount_type,description);
        call.enqueue(new Callback<AddTransactionResponse>() {
            @Override
            public void onResponse(Call<AddTransactionResponse> call, Response<AddTransactionResponse> response) {
                Toast.makeText(Transactions.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AddTransactionResponse> call, Throwable t) {
                Toast.makeText(Transactions.this, "Fail.."+t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
            }


        });


    }
}