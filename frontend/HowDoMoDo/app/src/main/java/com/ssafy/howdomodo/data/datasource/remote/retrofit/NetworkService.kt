package com.ssafy.howdomodo.data.datasource.remote.retrofit

import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface NetworkService {
    @POST("/users/login")
    fun login(
            @Body userData: JsonObject
    ): Call<LoginResponse>
}