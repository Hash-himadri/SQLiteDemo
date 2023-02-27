package com.example.sqlitedemo_b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.addStudent);
    }


    RegisterFragment registerFragment = new RegisterFragment();
    EditFragment editFragment = new EditFragment();
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.addStudent:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, registerFragment).commit();
                return true;
            case R.id.updateStudent:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, editFragment).commit();
                return true;
            default:
                return false;
        }
    }
}