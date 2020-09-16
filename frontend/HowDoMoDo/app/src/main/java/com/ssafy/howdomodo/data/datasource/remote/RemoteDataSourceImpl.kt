package com.ssafy.howdomodo.data.datasource.remote

import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.LoginResponse
import com.ssafy.howdomodo.data.datasource.remote.retrofit.NetworkServiceImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl : RemoteDataSource {
    val api = NetworkServiceImpl.SERVICE

    override fun login(userData: JsonObject, success: (LoginResponse, String?) -> Unit, fail: (Throwable) -> Unit
    ) {
        api.login(userData).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                success(response.body()!!, response.headers().get("AUTH"))
            }
        })
    }
}