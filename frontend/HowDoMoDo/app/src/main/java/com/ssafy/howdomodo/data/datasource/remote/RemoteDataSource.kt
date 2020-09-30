package com.ssafy.howdomodo.data.datasource.remote

import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.LoginResponse
import com.ssafy.howdomodo.data.datasource.model.SignUpResponse


interface RemoteDataSource {
    fun login(
        loginRequestBody: JsonObject,
        success: (LoginResponse, String?) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun signUp(
        signUpRequestBody: JsonObject,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    )
}