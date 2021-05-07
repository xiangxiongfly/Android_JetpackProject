package com.example.viewbindingdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {
    protected VB viewBinding;
    protected Context mContext;
    protected View mRootView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Class<VB> clz = (Class<VB>) ((ParameterizedType) type).getActualTypeArguments()[0];
            try {
                Method method = clz.getMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
                viewBinding = (VB) method.invoke(null, inflater, container, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootView = view;
        initView();
    }

    protected abstract void initView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewBinding = null;
    }
}
