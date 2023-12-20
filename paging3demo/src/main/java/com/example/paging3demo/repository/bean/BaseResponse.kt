package com.example.paging3demo.repository.bean

import com.google.gson.annotations.SerializedName

class BaseResponse {
    @SerializedName("items")
    val items: List<User> = emptyList()
}