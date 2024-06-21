package com.example.livedatademo.other

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.arch.core.util.Function
import androidx.lifecycle.*
import com.example.common.base.BaseActivity
import com.example.livedatademo.R
import com.example.livedatademo.bean.User

class OthersActivity : BaseActivity(R.layout.activity_others) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StockLiveData.get().observe(this, object : Observer<String> {
            override fun onChanged(t: String) {
                Log.e("TAG", "StockLiveData: ${t}")
            }
        })
    }

    fun onMapClick(view: View) {
        val userLiveData = MutableLiveData<User>()
        val mapLiveData = Transformations.map(userLiveData, object : Function<User, String> {
            override fun apply(user: User): String {
                return "姓名：${user.name} 年龄：${user.age} 地址：${user.address}"
            }
        })
        mapLiveData.observe(this, { t ->
            Log.e("TAG", "map: ${t}")
        })
        userLiveData.value = User("小明", 18, "上海市")
    }

    fun onSwitchMapClick(view: View) {
        val liveData1 = MutableLiveData<String>()
        val liveData2 = MutableLiveData<String>()
        val switchLiveData = MutableLiveData<Boolean>()
        val resultLiveData = Transformations.switchMap(
            switchLiveData,
            object : Function<Boolean, LiveData<String>> {
                override fun apply(input: Boolean): LiveData<String> {
                    if (input) {
                        return liveData1
                    } else {
                        return liveData2
                    }
                }
            })
        resultLiveData.observe(this, object : Observer<String> {
            override fun onChanged(t: String?) {
                Log.e("TAG", "$t")
            }
        })
        switchLiveData.value = false
        liveData1.value = "123"
        liveData2.value = "ABC"
    }

    fun onAddLiveDataClick(view: View) {
        val mediatorLiveData = MediatorLiveData<String>()
        val liveData1 = MutableLiveData<String>()
        val liveData2 = MutableLiveData<String>()
        mediatorLiveData.addSource(liveData1, object : Observer<String> {
            override fun onChanged(t: String?) {
                Log.e("TAG", "onChanged liveData1:$t")
                mediatorLiveData.value = t
            }
        })
        mediatorLiveData.addSource(liveData2, object : Observer<String> {
            override fun onChanged(t: String?) {
                Log.e("TAG", "onChanged liveData2:$t")
                mediatorLiveData.value = t
            }
        })
        mediatorLiveData.observe(this, object : Observer<String> {
            override fun onChanged(t: String?) {
                Log.e("TAG", "onChanged mediatorLiveData:$t")
            }
        })
        liveData1.value = "123"
        liveData2.value = "ABC"
    }
}