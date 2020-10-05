package com.ssafy.howdomodo.data.repository

import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.*
import com.ssafy.howdomodo.data.datasource.remote.MovieRemoteDataSource
import com.ssafy.howdomodo.data.datasource.remote.NaverRemoteDataSource
import com.ssafy.howdomodo.data.datasource.remote.RemoteDataSource


class MyPageRepository(private val remoteDataSource: RemoteDataSource) {

    fun getInfo(userCode:Int, success: (LoginResponse) -> Unit, fail: (Throwable) -> Unit) {
        remoteDataSource.userInfo(userCode,success,fail)
    }

    fun userUpdate(
        signUpRequestBody: JsonObject,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) = remoteDataSource.userUpdate(signUpRequestBody, success, fail)

    fun userDelete(
        userCode: Int,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) = remoteDataSource.userDelete(userCode, success, fail)

    fun userNickCheck(
        userNick: String,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) = remoteDataSource.userNickCheck(userNick, success, fail)
}