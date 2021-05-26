package com.example.navigationdemo.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.navigationdemo.R

class WelcomeFragment : Fragment() {

    private lateinit var btnToLogin: Button
    private lateinit var btnToRegister: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnToLogin = view.findViewById<Button>(R.id.btn_to_login)
        btnToRegister = view.findViewById<Button>(R.id.btn_to_register)

        //使用id导航
        btnToLogin.setOnClickListener {
            val navOptions = navOptions {
                anim {
                    enter = R.anim.common_slide_in_right
                    exit = R.anim.common_slide_out_left
                    popEnter = R.anim.common_slide_in_left
                    popExit = R.anim.common_slide_out_right
                }
            }
            val bundle = Bundle()
            bundle.putString("data", "1234567")
            findNavController().navigate(R.id.login, bundle, navOptions)
        }

        //使用SafeArags导航
        btnToRegister.setOnClickListener {
            val action = WelcomeFragmentDirections
                .actionToRegister()
                .setData("ABCDEFG")
            findNavController().navigate(action)
        }
    }
}