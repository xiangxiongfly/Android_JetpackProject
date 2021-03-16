package com.example.lifecyclerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLifecycle().addObserver(new MyObserver());
        Log.e(TAG, "onCreate()");
    }

    public void gotoActivity(View view) {
        startActivity(new Intent(this, TwoActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy()");
    }
}

class MyObserver implements LifecycleObserver {
    public static final String TAG = "TAG";

    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    private void onInit() {
        Log.e(TAG, "初始化");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onConnect(LifecycleOwner owner) {
        Log.e(TAG, "建立连接");
        if (owner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            Log.e(TAG, " --started");
        }
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_PAUSE)
    private void onDisconnect() {
        Log.e(TAG, "断开连接");
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_DESTROY)
    private void onRelease() {
        Log.e(TAG, "释放资源");
    }
}