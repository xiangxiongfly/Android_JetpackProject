package com.example.myapplication

import androidx.lifecycle.ViewModel
import com.example.myapplication.entity.BaseResponse
import com.example.myapplication.entity.DataResult
import com.example.myapplication.entity.Search
import com.example.myapplication.exceptions.ExceptionHandler
import com.example.myapplication.exceptions.ServerException
import com.example.myapplication.extensions.requestMain

class MainViewModel : ViewModel() {

    private val searchLiveData = SingleLiveData<DataResult<BaseResponse<Search>>>()

    fun search1(page: Int, keywords: String): SingleLiveData<DataResult<BaseResponse<Search>>> {
        requestMain {
            val mainModel = MainModel()
            try {
                val response = mainModel.search(page, keywords)
                if (response.errorCode == 0) {
                    searchLiveData.value = DataResult.Success(response)
                } else {
                    searchLiveData.value = DataResult.Error(
                        ExceptionHandler.handleException(ServerException(response.errorCode,
                            response.errorMsg)))
                }
            } catch (e: Exception) {
                searchLiveData.value = DataResult.Error(ExceptionHandler.handleException(e))
            }
        }
        return searchLiveData
    }

    fun search2(page: Int, keywords: String): SingleLiveData<DataResult<BaseResponse<Search>>> {
        requestMain {
            val mainModel = MainModel()
            when (val dataResult = mainModel.search2(page, keywords)) {
                is DataResult.Success -> {
                    searchLiveData.value = dataResult
                }
                is DataResult.Error -> {
                    searchLiveData.value = dataResult
                }
            }
        }
        return searchLiveData
    }
}