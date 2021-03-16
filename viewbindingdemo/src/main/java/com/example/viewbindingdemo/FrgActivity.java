package com.example.viewbindingdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FrgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frg);
        getSupportFragmentManager().beginTransaction().add(R.id.container, MyFragment.newInstance()).commit();
    }
}