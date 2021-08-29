package com.example.myapplication

import androidx.lifecycle.viewModelScope
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.entity.Banner
import com.example.myapplication.entity.BaseResponse
import com.example.myapplication.entity.DataResult
import com.example.myapplication.entity.Search
import com.example.myapplication.exceptions.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    private val _searchFlow = MutableStateFlow<DataResult<BaseResponse<Search>>>(DataResult.None)
    val searchFlow = _searchFlow.asStateFlow()

    private val _bannerFlow =
        MutableStateFlow<DataResult<BaseResponse<List<Banner>>>>(DataResult.None)
    val bannerFlow = _bannerFlow.asStateFlow()

    private val _bannerFlow2 =
        MutableStateFlow<DataResult<BaseResponse<List<Banner>>>>(DataResult.None)
    val bannerFlow2 = _bannerFlow2.asStateFlow()


    fun search() {
        viewModelScope.launch {
            repository.search(1, "鸿洋").collect {
                when (it) {
                    is DataResult.Error -> {
                        _searchFlow.value = it
                    }
                    is DataResult.Success -> {
                        _searchFlow.value = it
                    }
                }
            }
        }
    }

    fun getBanner() {
        viewModelScope.launch {
            request({ repository.getBanner() },
                {
                    _bannerFlow.value = DataResult.Start
                },
                {
                    _bannerFlow.value = DataResult.Success(it)
                },
                {
                    _bannerFlow.value = DataResult.Error(ExceptionHandler.handleException(it))
                }, {
                    _bannerFlow.value = DataResult.Completion
                })
        }
    }

    fun getBanner2() {
        viewModelScope.launch {
            request(_bannerFlow2) {
                repository.getBanner()
            }
        }
    }


}
