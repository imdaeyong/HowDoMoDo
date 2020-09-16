package com.ssafy.howdomodo.data.datasource.remote.retrofit

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface NetworkService {
    @POST("/users/signUp")
    fun signUp(
            @Body userName: JsonObject
    ): Call<String>

}