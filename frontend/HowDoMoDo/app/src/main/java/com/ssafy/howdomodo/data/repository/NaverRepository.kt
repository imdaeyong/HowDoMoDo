package com.ssafy.howdomodo.data.repository

import NaverRemoteDataSource
import com.ssafy.howdomodo.data.datasource.model.NaverApi


class NaverRepository(private val naverRemoteDataSource: NaverRemoteDataSource) {

    fun getBlogData(title: String, success: (NaverApi) -> Unit, fail: (Throwable) -> Unit) {
        naverRemoteDataSource.getBlogData(title,success,fail)
    }
}