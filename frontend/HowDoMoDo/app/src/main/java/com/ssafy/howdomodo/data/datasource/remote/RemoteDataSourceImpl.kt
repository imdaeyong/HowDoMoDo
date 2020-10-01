package com.ssafy.howdomodo.data.datasource.remote

import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.LoginResponse
import com.ssafy.howdomodo.data.datasource.model.SignUpResponse
import com.ssafy.howdomodo.data.datasource.remote.retrofit.NetworkServiceImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl : RemoteDataSource {
    val api = NetworkServiceImpl.SERVICE

    override fun login(
        loginRequestBody: JsonObject,
        success: (LoginResponse, String?) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        api.login(loginRequestBody).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                success(response.body()!!, response.headers().get("AUTH"))
            }
        })
    }

    override fun signUp(
        signUpRequestBody: JsonObject,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        api.signUp(signUpRequestBody).enqueue(object : Callback<SignUpResponse> {
            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                success(response.body()!!)
            }
        })
    }
}