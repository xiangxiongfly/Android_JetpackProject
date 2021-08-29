package com.example.myapplication.base

import com.example.myapplication.entity.BaseResponse
import com.example.myapplication.entity.DataResult
import com.example.myapplication.exceptions.ExceptionHandler
import com.example.myapplication.exceptions.ServerException

/**
 * 封装BaseModel
 * 数据转为DataResult
 */
open class BaseModel {
    suspend inline fun <T> launchRequestForResult(noinline block: suspend () -> T): DataResult<T> {
        return try {
            val response = block.invoke()
            if ((response as BaseResponse<*>).isSuccessful()) {
                DataResult.Success(response)
            } else {
                DataResult.Error(
                    ExceptionHandler.handleException(
                        ServerException(
                            response.errorCode,
                            response.errorMsg
                        )
                    )
                )
            }
        } catch (e: Exception) {
            return DataResult.Error(ExceptionHandler.handleException(e))
        }
    }
}