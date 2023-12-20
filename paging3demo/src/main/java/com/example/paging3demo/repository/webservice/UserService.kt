package com.example.paging3demo.repository.webservice

import com.example.paging3demo.network.NetworkManager
import com.example.paging3demo.repository.bean.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("search/repositories?sort=stars&q=Android")
    suspend fun searchUser(@Query("page") page: Int, @Query("per_page") perPager: Int): BaseResponse

    companion object {
        fun create(): UserService {
            return NetworkManager.retrofit.create(UserService::class.java)
        }
    }
}