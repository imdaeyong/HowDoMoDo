package com.ssafy.howdomodo.data.datasource.remote

import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.*


interface RemoteDataSource {
    fun login(
        loginRequestBody: JsonObject,
        success: (LoginResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun signUp(
        signUpRequestBody: JsonObject,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun userInfo(
        userCode:Int,
        success: (LoginResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun userUpdate(
        signUpRequestBody: JsonObject,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun userDelete(
        userCode:Int,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    )
    fun getTheaters(
        getTheatersRequestBody:JsonObject,
        success: (GetTheatersResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

}