package com.example.paging3demo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging3demo.repository.bean.User
import com.example.paging3demo.repository.data.UserRepo
import kotlinx.coroutines.flow.Flow

class MainViewModel(application: Application) : AndroidViewModel(application) {

    fun getPagingData(): Flow<PagingData<User>> {
        // cacheIn表示将结果缓存到ViewModelScope中，在onClear之前一直存在
        return UserRepo.getPagingData().cachedIn(viewModelScope)
    }
}