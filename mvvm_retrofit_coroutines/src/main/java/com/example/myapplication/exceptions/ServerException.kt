package com.example.myapplication.exceptions

/**
 * 服务器返回的错误code值（code!=0）
 */
class ServerException(val code: Int, val msg: String) : RuntimeException()