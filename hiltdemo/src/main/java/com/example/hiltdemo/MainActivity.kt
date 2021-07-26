package com.example.hiltdemo


import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.hiltdemo.entity.Car
import com.example.hiltdemo.entity.MyContext
import com.example.hiltdemo.entity.MyContext2
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

    private val viewModel by viewModels<MyViewModel>()

    @Inject
    lateinit var myContext: MyContext

    @Inject
    lateinit var myContext2: MyContext2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val stringBuilder = StringBuilder()

        log("$user")
        log("$user2")

        car.drive()

        log("okHttpClient: $okHttpClient")
        log("retrofit: $retrofit ")

        log("myContext : ${myContext.conext}  ${myContext.activityContext}")
        log("myContext2 : ${myContext2.application} ${myContext2.activity}")

        viewModel.loadData().observe(this) {
            log(it)
        }

    }
}