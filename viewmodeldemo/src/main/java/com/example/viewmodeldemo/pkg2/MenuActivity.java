package com.example.viewmodeldemo.pkg2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.viewmodeldemo.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.flMenu, MenuFragment.newInstance())
                .add(R.id.flDetail, DetailFragment.newInstance())
                .commit();
    }
}