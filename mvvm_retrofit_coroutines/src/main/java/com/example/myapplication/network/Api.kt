package com.example.myapplication.network

import com.example.myapplication.entity.Banner
import com.example.myapplication.entity.BaseResponse
import com.example.myapplication.entity.Search
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("article/list/{page}/json")
    suspend fun search(
        @Path("page") page: Int,
        @Query("author") author: String
    ): BaseResponse<Search>

    @GET("banner/json")
    suspend fun getBanners(): BaseResponse<List<Banner>>
}