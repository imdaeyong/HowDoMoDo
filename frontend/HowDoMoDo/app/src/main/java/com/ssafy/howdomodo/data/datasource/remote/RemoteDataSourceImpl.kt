package com.ssafy.howdomodo.data.datasource.remote

import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.remote.retrofit.NetworkServiceImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl : RemoteDataSource {
    val api = NetworkServiceImpl.SERVICE

    override fun signUp(userName: JsonObject, success: (String) -> Unit, fail: (Throwable) -> Unit) {
        api.signUp(userName).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                success("결과값")
            }
        })
    }
}