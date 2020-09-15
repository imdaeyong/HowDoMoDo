package com.ssafy.howdomodo.data.datasource.remote.retrofit


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkServiceImpl {
    private const val BASE_URL = "http://13.209.182.154:3456/"

    private val okHttpClient: OkHttpClient =
            OkHttpClient.Builder().build()

    private val retrofit: Retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

    val SERVICE: NetworkService = retrofit.create(
            NetworkService::class.java)
}