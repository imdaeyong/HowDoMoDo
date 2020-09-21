package com.ssafy.howdomodo.data.datasource.remote

import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.LoginResponse


interface RemoteDataSource {
    fun login(
            userData:JsonObject,
            success: (LoginResponse,String?) -> Unit,
            fail: (Throwable) -> Unit
    )
}