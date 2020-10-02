package com.ssafy.howdomodo.data.datasource.remote

import android.util.Log
import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.GetTheatersResponse
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
        success: (LoginResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        api.login(loginRequestBody).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.e("TEST2", response.body()?.data?.userEmail.toString())
//                response.body()?.let { success(it)}
                if (response.body() != null) {
                    success(response.body()!!)
                } else {
                    fail(Exception("유효한 정보가 없습니다"))
                }
//                success(response.body()!!)
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

    override fun userInfo(
        userCode: Int,
        success: (LoginResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        api.userInfo(userCode).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.body() != null) {
                    success(response.body()!!)
                } else {
                    fail(Exception("유효한 정보가 없습니다"))
                }
            }
        })
    }

    override fun userUpdate(
        signUpRequestBody: JsonObject,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        api.userUpdate(signUpRequestBody).enqueue(object : Callback<SignUpResponse> {
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

    override fun userDelete(
        userCode: Int,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        api.userDelete(userCode).enqueue(object : Callback<SignUpResponse> {
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

    override fun getTheaters(
        getTheatersRequestBody: JsonObject,
        success: (GetTheatersResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        api.getTheaters(getTheatersRequestBody).enqueue(object : Callback<GetTheatersResponse> {
            override fun onFailure(call: Call<GetTheatersResponse>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(
                call: Call<GetTheatersResponse>,
                response: Response<GetTheatersResponse>
            ) {
                success(response.body()!!)
            }
        })
    }
   
}