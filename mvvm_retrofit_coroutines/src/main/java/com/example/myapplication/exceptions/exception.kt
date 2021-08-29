package com.example.myapplication.exceptions

import kotlinx.coroutines.CoroutineExceptionHandler

val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    ExceptionHandler.handleException(throwable)
}
