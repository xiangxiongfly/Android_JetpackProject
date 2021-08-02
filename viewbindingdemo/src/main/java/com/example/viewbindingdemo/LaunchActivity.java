package com.example.viewbindingdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.viewbindingdemo.base.FirstActivity;
import com.example.viewbindingdemo.base2.SecondActivity;
import com.example.viewbindingdemo.rv.RvActivity;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }

    public void gotoActivity(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void gotoFrg(View view) {
        startActivity(new Intent(this, FrgActivity.class));
    }

    public void gotoRv(View view) {
        startActivity(new Intent(this, RvActivity.class));
    }

    public void gotoInclude(View view) {
        startActivity(new Intent(this, IncludeActivity.class));
    }

    public void gotoBaseActivity(View view) {
        startActivity(new Intent(this, FirstActivity.class));
    }

    public void gotoBaseActivity2(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}