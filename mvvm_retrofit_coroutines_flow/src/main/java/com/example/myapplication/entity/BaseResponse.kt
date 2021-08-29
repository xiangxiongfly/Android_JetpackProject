package com.example.myapplication.entity

data class BaseResponse<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String,
) {
    fun isSuccessful(): Boolean {
        return errorCode == 0
    }
}
