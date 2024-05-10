package com.sumit.skcreditappmain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class SearchCust extends AppCompatActivity {
    private SearchView sv_customer;
    private ListView lv_customer;

    ArrayList<String> Customers;
    ArrayAdapter<String> adapter;
    String[] customer = {
            "anuj", "aman", "ajay", "amit", "babalu", "baman", "chaman", "brijesh","chirkut",
            "chinni", "DAKU", "elaga"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_cust);

        getSupportActionBar().hide();

        sv_customer = (SearchView)findViewById(R.id.sv_customer);
        lv_customer = (ListView)findViewById(R.id.lv_customer);
        for(int i =0; i< customer.length;i++) {
            Customers.add(customer[i]);
        }
    }
}