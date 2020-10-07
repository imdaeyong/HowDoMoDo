package com.ssafy.howdomodo.data.repository

import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.SignUpResponse
import com.ssafy.howdomodo.data.datasource.remote.RemoteDataSource

class SignUpRepository(private val remoteDataSource: RemoteDataSource){
    fun signUp(
        signUpRequestBody: JsonObject,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) = remoteDataSource.signUp(signUpRequestBody, success, fail)

    fun userNickCheck(
        nickname: String,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) = remoteDataSource.userNickCheck(nickname, success, fail)

    fun userEmailCheck(
        email: String,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) = remoteDataSource.userNickCheck(email, success, fail)
}