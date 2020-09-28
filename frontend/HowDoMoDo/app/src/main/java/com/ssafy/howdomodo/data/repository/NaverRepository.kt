package com.ssafy.howdomodo.data.repository

import com.ssafy.howdomodo.data.datasource.model.NaverApi
import com.ssafy.howdomodo.data.datasource.remote.NaverRemoteDataSource


class NaverRepository(private val naverRemoteDataSource: NaverRemoteDataSource) {

    fun getBlogData(title: String, success: (NaverApi) -> Unit, fail: (Throwable) -> Unit) {
        var search = "영화 "+title
        naverRemoteDataSource.getBlogData(search,success,fail)
    }
}