package com.ssafy.howdomodo.data.repository

import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.LoginResponse
import com.ssafy.howdomodo.data.datasource.remote.RemoteDataSource

class LoginRepository(private val remoteDataSource: RemoteDataSource) {
    fun login(
        loginRequestBody: JsonObject,
        success: (LoginResponse, String?) -> Unit,
        fail: (Throwable) -> Unit
    ) = remoteDataSource.login(loginRequestBody, success, fail)
}