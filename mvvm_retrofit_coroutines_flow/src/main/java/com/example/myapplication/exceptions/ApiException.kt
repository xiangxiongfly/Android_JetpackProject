package com.example.myapplication.exceptions

/**
 * 异常封装
 */
class ApiException(val throwable: Throwable, val code: Int, var msg: String) :
    Exception(throwable)
