package com.example.myapplication.exceptions

import android.util.Log
import com.google.gson.JsonParseException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException
import javax.net.ssl.SSLHandshakeException

/**
 * 异常处理
 */
object ExceptionHandler {
    private const val HTTP = "http"

    //HTTP Code码
    private const val UNAUTHORIZED = 401;
    private const val FORBIDDEN = 403;
    private const val NOT_FOUND = 404;
    private const val REQUEST_TIMEOUT = 408;
    private const val INTERNAL_SERVER_ERROR = 500;
    private const val BAD_GATEWAY = 502;
    private const val SERVICE_UNAVAILABLE = 503;
    private const val GATEWAY_TIMEOUT = 504;

    fun handleException(e: Throwable): ApiException {
        when (e) {
            //服务器返回错误
            is ServerException -> {
                Log.e(HTTP, "服务器返回错误")
                return ApiException(e, e.code, e.msg)
            }
            //HTTP状态错误
            is HttpException -> {
                Log.e(HTTP, "网络错误")
                return ApiException(e, Error.HTTP_ERROR, "网络错误")
            }
            //解析错误
            is JsonParseException,
            is JSONException,
            is ParseException,
            -> {
                Log.e(HTTP, "解析错误")
                return ApiException(e, Error.PARSE_ERROR, "解析错误")
            }
            //网络错误
            is ConnectException,
            is UnknownHostException,
            is ConnectTimeoutException,
            is SocketTimeoutException,
            -> {
                Log.e(HTTP, "网络连接失败")
                return ApiException(e, Error.NETWORK_ERROR, "网络连接失败")
            }
            //证书验证失败
            is SSLHandshakeException -> {
                Log.e(HTTP, "证书验证失败")
                return ApiException(e, Error.SSL_ERROR, "证书验证失败")
            }
            //未知错误
            else -> {
                Log.e(HTTP, "未知错误")
                return ApiException(e, Error.UNKNOWN, "未知错误")
            }
        }
    }
}

object Error {
    //未知错误
    const val UNKNOWN = 9000

    //解析错误
    const val PARSE_ERROR = 9001

    //网络错误
    const val NETWORK_ERROR = 9002

    //协议错误
    const val HTTP_ERROR = 9003

    //证书错误
    const val SSL_ERROR = 9004

    //连接超时
    const val TIMEOUT_ERROR = 9006
}




