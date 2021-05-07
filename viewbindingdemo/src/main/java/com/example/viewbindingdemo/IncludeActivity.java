package com.example.viewbindingdemo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.viewbindingdemo.databinding.ActivityIncludeBinding;
import com.example.viewbindingdemo.databinding.DetailLayoutBinding;

public class IncludeActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        ActivityIncludeBinding binding = ActivityIncludeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //不带merge标签使用
        binding.titleBar.title.setText("这是一个标题");
        binding.titleBar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "返回", Toast.LENGTH_SHORT).show();
            }
        });
        binding.titleBar.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "确定", Toast.LENGTH_SHORT).show();
            }
        });

        //带merge标签使用
        DetailLayoutBinding detailBinding = DetailLayoutBinding.bind(binding.getRoot());
        detailBinding.ivDetail.setImageResource(R.mipmap.ic_launcher);
    }
}