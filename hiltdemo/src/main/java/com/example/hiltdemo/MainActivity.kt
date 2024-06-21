package com.example.hiltdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.hiltdemo.entity.Car
import com.example.hiltdemo.entity.MyAppAndActivity
import com.example.hiltdemo.entity.MyContext
import com.example.hiltdemo.entity.User
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var user: User

    @Inject
    lateinit var user2: User

    @Inject
    lateinit var car: Car

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var myContext: MyContext

    @Inject
    lateinit var my: MyAppAndActivity

    @Inject
    lateinit var baseApplication: BaseApplication

    private val viewModel by viewModels<MyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        log("$user")
//        log("$user2")
//        user.sayHello()
//        user2.sayHello()
//
//        car.start()

//        log("okHttpClient: $okHttpClient")
//        log("retrofit: $retrofit ")

//        log("appContext : ${myContext.appContext}")
//        log("activityContext : ${myContext.activityContext}")

//        log("application : ${my.application}")
//        log("activity : ${my.activity}")

//        log("baseApplication: $baseApplication")

        viewModel.loadData().observe(this) {
            log(it)
        }

    }

    fun toFragmentActivity(view: View) {
        startActivity(Intent(this, FragmentWidthActivity::class.java))
    }
}