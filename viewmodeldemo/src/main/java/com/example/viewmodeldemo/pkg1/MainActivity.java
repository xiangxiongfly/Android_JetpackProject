package com.example.viewmodeldemo.pkg1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.viewmodeldemo.Constant;
import com.example.viewmodeldemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
        TextView tvUser = findViewById(R.id.tvUser);
        ProgressBar pbLoading = findViewById(R.id.pbLoading);

        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        UserViewModel userViewModel = viewModelProvider.get(UserViewModel.class);

        userViewModel.getUser().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvUser.setText(s);
            }
        });
        userViewModel.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                pbLoading.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userViewModel.getUserInfo();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(Constant.TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(Constant.TAG, "onDestroy()");
    }
}