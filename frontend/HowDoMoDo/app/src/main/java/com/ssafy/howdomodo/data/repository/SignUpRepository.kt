package com.ssafy.howdomodo.data.repository

import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.remote.RemoteDataSource

class SignUpRepository(private val remoteDataSource: RemoteDataSource){
    fun signUp(userName: JsonObject, success: (String) -> Unit, fail: (Throwable) -> Unit) = remoteDataSource.signUp(userName, success, fail)
}