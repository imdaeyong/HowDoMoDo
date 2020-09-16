package com.ssafy.howdomodo.data.datasource.remote

import com.google.gson.JsonObject


interface RemoteDataSource {
    fun signUp(
            userName: JsonObject,
            success: (String) -> Unit,
            fail: (Throwable) -> Unit
    )
}