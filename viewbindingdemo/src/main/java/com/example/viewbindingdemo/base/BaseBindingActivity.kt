package com.example.viewbindingdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity {
    protected VB viewBinding;
    protected Context mContext;
    protected View mRootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Class<VB> clz = (Class<VB>) ((ParameterizedType) type).getActualTypeArguments()[0];
            try {
                Method method = clz.getMethod("inflate", LayoutInflater.class);
                viewBinding = (VB) method.invoke(null, getLayoutInflater());
            } catch (Exception e) {
                e.printStackTrace();
            }
            mRootView = viewBinding.getRoot();
            setContentView(mRootView);
        }
        initView();
    }

    protected abstract void initView();

}
