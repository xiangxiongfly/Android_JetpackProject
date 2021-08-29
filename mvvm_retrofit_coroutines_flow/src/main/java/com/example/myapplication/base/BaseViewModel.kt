package com.example.myapplication.base

import androidx.lifecycle.ViewModel
import com.example.myapplication.entity.BaseResponse
import com.example.myapplication.entity.DataResult
import com.example.myapplication.exceptions.ExceptionHandler
import com.example.myapplication.exceptions.ServerException
import kotlinx.coroutines.flow.*

open class BaseViewModel : ViewModel() {

    suspend fun <T> request(
        block: suspend () -> Flow<BaseResponse<T>>,
        onStart: (() -> Unit)? = null,
        onSuccess: (BaseResponse<T>) -> Unit,
        onError: (Exception) -> Unit,
        onCompletion: (() -> Unit)? = null,
    ) {
        block()
            .onStart {
                onStart?.invoke()
            }
            .catch {
                onError.invoke(ExceptionHandler.handleException(it))
            }
            .onCompletion {
                onCompletion?.invoke()
            }
            .collect {
                if (it.isSuccessful()) {
                    onSuccess.invoke(it)
                } else {
                    onError.invoke(ExceptionHandler.handleException(ServerException(it.errorCode,
                        it.errorMsg)))
                }
            }
    }

    suspend fun <T> request(
        flow: MutableStateFlow<DataResult<BaseResponse<T>>>,
        block: suspend () -> Flow<BaseResponse<T>>,
    ) {
        block()
            .onStart {
                flow.value = DataResult.Start
            }
            .catch {
                flow.value = DataResult.Error(ExceptionHandler.handleException(it))
            }
            .onCompletion {
                flow.value = DataResult.Completion
            }
            .collect {
                if (it.isSuccessful()) {
                    flow.value = DataResult.Success(it)
                } else {
                    flow.value =
                        DataResult.Error(ExceptionHandler.handleException(ServerException(it.errorCode,
                            it.errorMsg)))
                }
            }
    }

}