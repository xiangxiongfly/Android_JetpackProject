package com.example.myapplication

import com.example.myapplication.base.BaseApp
import com.example.myapplication.db.Cache
import com.example.myapplication.db.DBCache
import com.example.myapplication.entity.Banner
import com.example.myapplication.entity.BaseResponse
import com.example.myapplication.entity.DataResult
import com.example.myapplication.entity.Search
import com.example.myapplication.exceptions.ExceptionHandler
import com.example.myapplication.exceptions.ServerException
import com.example.myapplication.network.Api
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor() {

    @Inject
    lateinit var api: Api

    suspend fun search(page: Int, keywords: String): Flow<DataResult<BaseResponse<Search>>> {
        return flow {
            val cacheDao = DBCache.getDatabase(BaseApp.context).cacheDao()
            val cache = cacheDao.getCache("search")
            cache?.let {
                val search: BaseResponse<Search> = Gson().fromJson(it.value,
                    object : TypeToken<BaseResponse<Search>>() {
                    }.type)
                emit(DataResult.Success(search))
            }

            val search = api.search(page, keywords)
            if (search.isSuccessful()) {
                emit(DataResult.Success(search))
                cacheDao.saveCache(Cache("search", Gson().toJson(search)))
            } else {
                emit(DataResult.Error(ExceptionHandler.handleException(ServerException(search.errorCode,
                    search.errorMsg))))
            }
        }.flowOn(Dispatchers.IO)
            .catch {
                emit(DataResult.Error(ExceptionHandler.handleException(it)))
            }
    }

    suspend fun getBanner(): Flow<BaseResponse<List<Banner>>> {
        return flow {
            val banner = api.getBanners()
            emit(banner)
        }.flowOn(Dispatchers.IO)
    }

}


