package com.ssafy.howdomodo.data.datasource.remote.retrofit

import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.LoginResponse
import com.ssafy.howdomodo.data.datasource.model.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface NetworkService {
    @POST("/users/login")
    fun login(
            @Body loginRequestBody: JsonObject
    ): Call<LoginResponse>

    @POST("/users/join")
    fun signUp(
        @Body signUpRequestBody: JsonObject
    ): Call<SignUpResponse>
}