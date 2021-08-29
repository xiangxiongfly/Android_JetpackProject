package com.example.mvvmdemo.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmdemo.model.Callback;
import com.example.mvvmdemo.model.UserInfo;
import com.example.mvvmdemo.model.UserInfoModel;

public class UserInfoViewModel extends ViewModel {
    private MutableLiveData<UserInfo> userInfoLiveData;
    private MutableLiveData<Boolean> loadingLiveData;
    private final UserInfoModel model;

    public UserInfoViewModel() {
        userInfoLiveData = new MutableLiveData<>();
        loadingLiveData = new MutableLiveData<>();
        model = new UserInfoModel();
    }

    public void getUserInfo() {
        loadingLiveData.setValue(true);
        model.getUserInfo(new Callback<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                loadingLiveData.setValue(false);
                userInfoLiveData.setValue(userInfo);
            }

            @Override
            public void onFailure(String msg) {
                loadingLiveData.setValue(false);
            }
        });

    }

    public MutableLiveData<UserInfo> getUserInfoLiveData() {
        return userInfoLiveData;
    }

    public MutableLiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }
}
