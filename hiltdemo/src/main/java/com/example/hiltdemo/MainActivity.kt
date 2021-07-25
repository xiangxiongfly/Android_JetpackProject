package com.example.hiltdemo


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hiltdemo.entity.Car
import com.example.hiltdemo.entity.MyContext
import com.example.hiltdemo.entity.User
import dagger.hilt.android.AndroidEntryPoint
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
    lateinit var myContext: MyContext

    @Inject
    lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        log("$user")
        log("$user2")

        car.drive()

        log("Activity : ${myContext.conext}  ${myContext.activityContext}")

        viewModel.loadData().observe(this) {
            log(it)
        }

    }
}