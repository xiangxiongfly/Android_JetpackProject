package com.example.viewmodeldemo.pkg1;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class UserViewModel extends ViewModel {

    private final MutableLiveData<String> user;
    private final MutableLiveData<Boolean> loading;
    private int count = 0;

    public UserViewModel() {
        user = new MutableLiveData<>();
        loading = new MutableLiveData<>();
    }

    //模拟网络请求
    public void getUserInfo() {
        loading.setValue(true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loading.setValue(false);
                user.setValue("hello ViewModel " + count++);
            }
        }, 2000);
    }

    public LiveData<String> getUser() {
        return user;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }
}
