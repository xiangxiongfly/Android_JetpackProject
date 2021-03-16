package com.example.viewmodeldemo.pkg2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareViewModel extends ViewModel {

    private final MutableLiveData<String> liveData;

    public ShareViewModel() {
        liveData = new MutableLiveData<>();
    }

    public LiveData<String> getLiveData() {
        return liveData;
    }

    public void select(String menu) {
        liveData.setValue(menu + " detail");
    }
}
