package com.example.hiltdemo

import javax.inject.Inject

class Repository @Inject constructor() {

    fun getData() = "从仓库获取了数据"
}