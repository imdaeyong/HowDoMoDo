package com.ssafy.howdomodo.data.repository

import com.ssafy.howdomodo.data.datasource.model.LoginResponse
import com.ssafy.howdomodo.data.datasource.model.MovieApi
import com.ssafy.howdomodo.data.datasource.model.NaverApi
import com.ssafy.howdomodo.data.datasource.model.Users
import com.ssafy.howdomodo.data.datasource.remote.MovieRemoteDataSource
import com.ssafy.howdomodo.data.datasource.remote.NaverRemoteDataSource
import com.ssafy.howdomodo.data.datasource.remote.RemoteDataSource


class MyPageRepository(private val remoteDataSource: RemoteDataSource) {

    fun getInfo(userCode:String, success: (LoginResponse) -> Unit, fail: (Throwable) -> Unit) {
        remoteDataSource.userInfo(userCode,success,fail)
    }
}