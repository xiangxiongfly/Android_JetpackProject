package com.example.livedatademo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "TAG";

    private MutableLiveData<String> liveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        liveData = new MutableLiveData<>();
        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.e(TAG, "onChanged: " + s);
            }

        });

        liveData.setValue("onCreate");
        liveData.setValue("onCreate2");
        liveData.setValue("onCreate3");
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Log.e(TAG, "onStart()");
//        liveData.setValue("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Log.e(TAG, "onResume");
//        liveData.setValue("onResume");


        //Map
//        MutableLiveData<String> liveData = new MutableLiveData<>();
//        LiveData<String> liveDataMap = Transformations.map(liveData, new Function<String, String>() {
//            @Override
//            public String apply(String input) {
//                String str = input + " LiveData";
//                Log.e(TAG, "apply: " + str);
//                return str;
//            }
//        });
//        liveDataMap.observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                Log.e(TAG, "onChanged: " + s);
//            }
//        });
//        liveData.setValue("hello");


        //switchMap
//        MutableLiveData<String> liveData1 = new MutableLiveData<>();
//        MutableLiveData<String> liveData2 = new MutableLiveData<>();
//        MutableLiveData<Boolean> switchLiveData = new MutableLiveData<>();
//
//        LiveData<String> resultLiveData = Transformations.switchMap(switchLiveData, new Function<Boolean, LiveData<String>>() {
//            @Override
//            public LiveData<String> apply(Boolean input) {
//                if (input) {
//                    return liveData1;
//                } else {
//                    return liveData2;
//                }
//            }
//        });
//
//        resultLiveData.observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                Log.e(TAG, "onChanged: " + s);
//            }
//        });
//
//        switchLiveData.postValue(true);
//        liveData1.postValue("ABC");
//        liveData2.postValue("123");


        MutableLiveData<String> liveData1 = new MutableLiveData<>();
        MutableLiveData<String> liveData2 = new MutableLiveData<>();
        MediatorLiveData<String> mediatorLiveData = new MediatorLiveData<>();

        mediatorLiveData.addSource(liveData1, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.e(TAG, "onChanged liveData1: " + s);
//                mediatorLiveData.setValue(s);
            }
        });

        mediatorLiveData.addSource(liveData2, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.e(TAG, "onChanged liveData2: " + s);
                mediatorLiveData.setValue(s);
            }
        });

        mediatorLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.e(TAG, "onChanged: " + s);
            }
        });

        liveData1.setValue("ABC");
//        liveData2.setValue("123");
//        mediatorLiveData.setValue("hello LiveData");

    }

    @Override
    protected void onPause() {
        super.onPause();
//        Log.e(TAG, "onPause()");
//        liveData.setValue("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Log.e(TAG, "onStop()");
//        liveData.setValue("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Log.e(TAG, "onDestroy()");
//        liveData.setValue("onDestroy");
    }
}