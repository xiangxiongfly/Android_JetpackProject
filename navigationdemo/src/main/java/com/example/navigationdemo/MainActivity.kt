package com.example.navigationdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.navigationdemo.login.WelcomeActivity
import com.example.navigationdemo.tab.TabActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun click1(view: View) {
        startActivity(Intent(this@MainActivity, WelcomeActivity::class.java))
    }

    fun click2(View: View) {
        startActivity(Intent(this@MainActivity, TabActivity::class.java))
    }

}