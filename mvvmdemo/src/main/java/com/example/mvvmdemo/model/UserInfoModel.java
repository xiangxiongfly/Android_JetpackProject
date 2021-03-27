package com.example.mvvmdemo.model;

import android.os.Handler;
import android.os.Looper;

public class UserInfoModel {

    public void getUserInfo(Callback<UserInfo> callback) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                UserInfo userInfo = new UserInfo("Tom", 18, "beijing");
                callback.onSuccess(userInfo);
            }
        }, 2000);
    }

}
