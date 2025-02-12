package com.example.hiltdemo

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hiltdemo.annotation.Db
import com.example.hiltdemo.annotation.File
import com.example.hiltdemo.di.ApiService
import com.example.hiltdemo.di.MyActivity
import com.example.hiltdemo.di.MyApp
import com.example.hiltdemo.entity.*
import com.example.hiltdemo.utils.Storage
import com.example.hiltdemo.utils.log
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var car: Car

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Inject
    lateinit var retrofit: Retrofit

    @File
    @Inject
    lateinit var storage1: Storage

    @Db
    @Inject
    lateinit var storage2: Storage

    @Inject
    lateinit var myContext: MyContext

    @Inject
    lateinit var mySingleton: MySingleton

    @Inject
    lateinit var myActivityScope: MyActivityScope

    @Inject
    lateinit var myActivityRetainedScope: MyActivityRetainedScope

    @Inject
    lateinit var myApp: MyApp

    @Inject
    lateinit var myActivity: MyActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        car.start()
        apiService.doSomeSth()
        log("retrofit:$retrofit")
        log("okHttpClient:$okHttpClient")
        storage1.save()
        storage2.save()
        myContext.show()
        myApp.show()
        myActivity.show()
    }

    override fun onResume() {
        super.onResume()
        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> log("横屏")
            Configuration.ORIENTATION_PORTRAIT -> log("竖屏")
        }
        mySingleton.test()
        myActivityScope.test()
        myActivityRetainedScope.test()
    }
}