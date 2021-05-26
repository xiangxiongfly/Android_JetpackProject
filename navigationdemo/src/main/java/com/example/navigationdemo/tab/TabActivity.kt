package com.example.navigationdemo.tab

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import com.example.navigationdemo.R

class TabActivity : AppCompatActivity() {
    private lateinit var switchFirst: TextView
    private lateinit var switchSecond: TextView
    private lateinit var switchThird: TextView

    val navOptions = navOptions {
        anim {
            enter = R.anim.common_fade_in
            exit = R.anim.common_fade_out
            popEnter = R.anim.common_fade_in
            popExit = R.anim.common_fade_out
        }
    }
    val bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)
        switchFirst = findViewById<TextView>(R.id.switch_first)
        switchSecond = findViewById<TextView>(R.id.switch_second)
        switchThird = findViewById<TextView>(R.id.switch_third)

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.my_tab_fragment) as NavHostFragment
//        val navController = navHostFragment.navController

        val navController = findNavController(R.id.my_tab_fragment)

        switchFirst.setOnClickListener {
            navController.navigate(R.id.first_fragment, bundle, navOptions)
        }
        switchSecond.setOnClickListener {
            navController.navigate(R.id.second_fragment, bundle, navOptions)
        }
        switchThird.setOnClickListener {
            navController.navigate(R.id.third_fragment, bundle, navOptions)
        }

    }
}