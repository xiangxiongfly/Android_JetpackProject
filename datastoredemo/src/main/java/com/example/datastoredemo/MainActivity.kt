package com.example.datastoredemo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.example.datastoredemo.exts.dataStore
import com.example.datastoredemo.exts.personDataStore
import com.example.datastoredemo.utils.logE
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getData(view: View) {
        lifecycleScope.launch {
            dataStore.edit { preferences ->
                // 先通过 stringPreferencesKey() 方法获取指定Key
                val nameKey = stringPreferencesKey("name")
                val ageKey = intPreferencesKey("age")
                val sexKey = booleanPreferencesKey("sex")
                // 通过Key获取值
                val name = preferences[nameKey]
                val age = preferences[ageKey]
                val sex = preferences[sexKey]
                logE("name:$name age:$age sex:$sex")
            }
        }
    }

    fun saveData(view: View) {
        lifecycleScope.launch {
            dataStore.edit { preferences ->
                preferences[stringPreferencesKey("name")] = "小明"
                preferences[intPreferencesKey("age")] = 18
                preferences[booleanPreferencesKey("sex")] = true
            }
        }
    }

    fun removeData(view: View) {
        lifecycleScope.launch {
            dataStore.edit { preferences ->
                val removeValue = preferences.remove(stringPreferencesKey("name"))
                logE("remove:$removeValue")
            }
        }
    }

    fun updateData(view: View) {
        lifecycleScope.launch {
            dataStore.edit { preferences ->
                preferences[stringPreferencesKey("name")] = "小黑"
                preferences[intPreferencesKey("age")] = 28
                preferences[booleanPreferencesKey("sex")] = false
            }
        }
    }

    fun clearData(view: View) {
        lifecycleScope.launch {
            dataStore.edit { preferences ->
                preferences.clear()
            }
        }
    }

    fun getCustomData(view: View) {
        lifecycleScope.launch {
            personDataStore.data.first().let { preferences ->
                val name = preferences.name
                val age = preferences.age
                val sex = preferences.sex
                val address = preferences.addressList
                val fruits = preferences.fruitsMap
                logE("name:$name age:$age sex:$sex address:$address fruits:$fruits")
            }
        }
    }

    fun saveCustomData(view: View) {
        lifecycleScope.launch {
            personDataStore.updateData { preferences ->
                // 方式一：
//                preferences.toBuilder()
//                    .setName("小白")
//                    .setAge(28)
//                    .setSex(true)
//                    .addAddress("广东省")
//                    .addAddress("广州市")
//                    .addAddress("黄埔区")
//                    .putFruits("apple", "苹果")
//                    .putFruits("banner", "香蕉")
//                    .putFruits("cherry", "樱桃")
//                    .build()

                // 方式二:
                preferences.toBuilder()
                    .setName("小白")
                    .setAge(28)
                    .setSex(true)
                    .addAllAddress(listOf("广东省", "广州市", "黄埔区"))
                    .putAllFruits(mapOf("apple" to "苹果", "banner" to "香蕉", "cherry" to "樱桃"))
                    .build()
            }
        }
    }

    fun updateCustomData(view: View) {
        lifecycleScope.launch {
            personDataStore.updateData { preferences ->
                preferences.toBuilder()
                    .setName("小黑")
                    .setAge(38)
                    .setSex(false)
                    .setAddress(0, "湖南省")
                    .setAddress(1, "长沙市")
                    .setAddress(2, "芙蓉区")
                    .putFruits("apple", "苹果1号")
                    .build()
            }
        }
    }

    fun removeCustomData(view: View) {
        lifecycleScope.launch {
            personDataStore.updateData { preferences ->
                preferences.toBuilder()
                    .removeFruits("apple")  // 删除map数据
                    .build()
            }
        }
    }

    fun clearCustomData(view: View) {
        lifecycleScope.launch {
            personDataStore.updateData { preferences ->

                // 清除所有数据
                preferences.toBuilder()
                    .clear()
                    .build()

                // 依次清除数据
//                preferences.toBuilder()
//                    .clearName()
//                    .clearAge()
//                    .clearSex()
//                    .clearAddress()
//                    .clearFruits()
//                    .build()
            }
        }
    }
}