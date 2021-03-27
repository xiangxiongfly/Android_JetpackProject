package com.example.mvvmdemo.model;

public interface Callback<T> {
    void onSuccess(T t);

    void onFailure(String msg);
}
