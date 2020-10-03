package com.ssafy.howdomodo.data.datasource.remote

import android.util.Log
import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.FavoritesResponse
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
        siName:String,
        guName:String,
        success: (GetTheatersResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        api.getTheaters(siName,guName).enqueue(object : Callback<GetTheatersResponse> {
            override fun onFailure(call: Call<GetTheatersResponse>, t: Throwable) {
                Log.e("remotedatasourceImpl", "failed!!!!")
                fail(t)
            }

            override fun onResponse(
                call: Call<GetTheatersResponse>,
                response: Response<GetTheatersResponse>
            ) {
                Log.e("remotedatasourceImpl", "onresponse join")

                if(response.body()!=null) {
                    Log.e("remotedatasourceimpl", response.body().toString())
                    success(response.body()!!)
                }else {
                    Log.e("remotedatasourceimple","여기서에러임.")
                    fail(Exception("유효한 데이터가 아닙니다"))
                }
            }

        })
    }



    override fun favoritesInfo(
        userCode:Int,
        success :(FavoritesResponse) ->Unit,
        fail : (Throwable) ->Unit
    ) {
        api.favoritesInfo(userCode).enqueue(object : Callback<FavoritesResponse> {
            override fun onFailure(call: Call<FavoritesResponse>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(call: Call<FavoritesResponse>, response: Response<FavoritesResponse>) {
                if (response.body() != null) {
                    success(response.body()!!)
                } else {
                    fail(Exception("유효한 즐겨찾기 정보가 없습니다"))
                }
            }
        })
    }

    override fun favoritesAdd(
        favoritesRequestBody:JsonObject,
        success :(FavoritesResponse) ->Unit,
        fail : (Throwable) ->Unit
    ){
        api.favoritesAdd(favoritesRequestBody).enqueue(object : Callback<FavoritesResponse> {
            override fun onFailure(call: Call<FavoritesResponse>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(
                call: Call<FavoritesResponse>,
                response: Response<FavoritesResponse>
            ) {
                success(response.body()!!)
            }
        })
    }


    override fun favoritesDelete(
        userCode:Int,
        theaterId:Int,
        success :(FavoritesResponse) ->Unit,
        fail : (Throwable) ->Unit
    ){

        api.favoritesDelete(userCode,theaterId).enqueue(object : Callback<FavoritesResponse> {
            override fun onFailure(call: Call<FavoritesResponse>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(
                call: Call<FavoritesResponse>,
                response: Response<FavoritesResponse>
            ) {
                success(response.body()!!)
            }
        })

    }

}