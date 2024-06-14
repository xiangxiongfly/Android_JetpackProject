package com.example.viewmodeldemo.user

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.common.base.BaseActivity
import com.example.viewmodeldemo.R

class UserActivity : BaseActivity() {
    private lateinit var tvUser: TextView

//    private val viewModel: UserViewModel by lazy {
//        val factory = UserViewModelFactory(User("小白", 18, arrayOf("北京市", "朝阳区")))
//        ViewModelProvider(this, factory).get(UserViewModel::class.java)
//    }

//    private val viewModel: UserViewModel by viewModels {
//        UserViewModelFactory(
//            User(
//                "小白",
//                18,
//                arrayOf("北京市", "朝阳区")
//            )
//        )
//    }

    private val viewModel: UserViewModel by viewModels {
        UserViewModel.provideFactory(User("小白", 18, arrayOf("北京市", "朝阳区")))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        tvUser = findViewById(R.id.tv_user)

        viewModel.userLiveData.observe(this, object : Observer<User> {
            override fun onChanged(user: User) {
                tvUser.text = "${user}"
            }
        })
    }

    fun onShow(view: View) {
        viewModel.showUser()
    }

    fun onChange(view: View) {
        viewModel.changeUser()
    }
}