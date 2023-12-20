package com.example.paging3demo.repository.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.paging3demo.repository.bean.User
import com.example.paging3demo.repository.webservice.UserService
import kotlinx.coroutines.flow.Flow

object UserRepo {
    private const val PAGE_SIZE = 15
    private val userService = UserService.create()

    fun getPagingData(): Flow<PagingData<User>> {
        // 配置Pager
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { UserPagingSource(userService) }
        ).flow
    }
}