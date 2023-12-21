package com.example.paging3demo.repository.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3demo.repository.bean.User
import com.example.paging3demo.repository.webservice.UserService

/**
 * 定义数据源
 * 需要PagingSource接口，PagingSource有2个泛型参数：
 *  第一个参数Key表示第几页Int类型
 *  第二个参数Value表示请求类型User类型
 */
class UserPagingSource(private val userService: UserService) : PagingSource<Int, User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            // 获取当前页数，params.key为null时，默认加载第1页
            val page = params.key ?: 1
            // 获取每页的数据
            val pageSize = params.loadSize
            // 网络请求：
            val response = userService.searchUser(page, pageSize)
            val data = response.items
            // 获取前一页索引
            val prevKey = if (page > 1) page - 1 else null
            // 获取后一页索引
            val nextKey = if (data.isNotEmpty()) page + 1 else null
            // 返回分页数据
            LoadResult.Page(data, prevKey, nextKey)
        } catch (e: Exception) {
            // 请求失败返回错误状态
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return null
    }
}