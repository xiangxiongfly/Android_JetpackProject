package com.example.mvvmdemo.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.mvvmdemo.databinding.ActivityMainBinding;
import com.example.mvvmdemo.model.UserInfo;
import com.example.mvvmdemo.viewmodel.UserInfoViewModel;

public class MainActivity extends AppCompatActivity {

    private UserInfoViewModel userInfoViewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        userInfoViewModel = new UserInfoViewModel();
        userInfoViewModel.getLoadingLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showLoading();
                } else {
                    hideLoading();
                }
            }
        });
        userInfoViewModel.getUserInfoLiveData().observe(this, new Observer<UserInfo>() {
            @Override
            public void onChanged(UserInfo userInfo) {
                viewBinding.tvUserInfo.setText(
                        String.format("姓名：%s，年龄：%d，地址：%s",
                                userInfo.getName(), userInfo.getAge(), userInfo.getAddress())
                );
            }
        });

        viewBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInfoViewModel.getUserInfo();
            }
        });
    }

    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载");
        }
        progressDialog.show();
    }

    public void hideLoading() {
        progressDialog.hide();
    }
}