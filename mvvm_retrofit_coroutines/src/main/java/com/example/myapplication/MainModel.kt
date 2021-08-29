package com.example.myapplication

import com.example.myapplication.base.BaseModel
import com.example.myapplication.entity.Banner
import com.example.myapplication.entity.BaseResponse
import com.example.myapplication.entity.DataResult
import com.example.myapplication.entity.Search
import com.example.myapplication.network.Api
import com.example.myapplication.network.HttpManager

class MainModel : BaseModel() {

    suspend fun search(page: Int, keywords: String): BaseResponse<Search> {
        return HttpManager.create(Api::class.java).search(page, keywords)
    }

    suspend fun search2(page: Int, keywords: String): DataResult<BaseResponse<Search>> {
        return launchRequestForResult {
            HttpManager.create(Api::class.java).search(page, keywords)
        }
    }

    suspend fun getBanners(): BaseResponse<List<Banner>> {
        return HttpManager.create(Api::class.java).getBanners()
    }
}