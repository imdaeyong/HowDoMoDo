package com.ssafy.howdomodo.data.datasource.remote.retrofit


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkServiceImpl {
    private const val BASE_URL = "http://j3a305.p.ssafy.io:8080/"
    private const val BASE_URL2 = "http://j3a305.p.ssafy.io:8000/"

    private val okHttpClient: OkHttpClient =
        OkHttpClient.Builder().build()

    private val retrofit: Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val SERVICE: NetworkService = retrofit.create(
        NetworkService::class.java
    )

    private val retrofit2: Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL2).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    val SERVICE2: NetworkService = retrofit2.create(
        NetworkService::class.java
    )
}