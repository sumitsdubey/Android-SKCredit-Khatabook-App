package com.sumit.skcreditappmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {

    BottomNavigationView bottom_nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        bottom_nav = (BottomNavigationView)findViewById(R.id.bottom_nav);

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                Fragment temp = null;
                switch (id){
                    case R.id.home :
                        temp = new HomeFragment();
                        break;
                    case R.id.search :
                        temp = new SearchFragment();
                        break;
                    case R.id.history :
                        temp = new HistoryFragment();
                        break;
                    case R.id.profile :
                        temp = new ProfileFragment();
                        break;
                    case R.id.more :
                        temp = new MoreFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, temp).commit();
                return true;
            }
        });
    }

    public void logout(View view) {
        Intent i = new Intent(Dashboard.this, WelcomeScreen.class);
        startActivity(i);
        finish();
        SharedPreferences shrd = getSharedPreferences("user_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = shrd.edit();
        editor.remove("username");
        editor.remove("password");
        editor.commit();

    }

}