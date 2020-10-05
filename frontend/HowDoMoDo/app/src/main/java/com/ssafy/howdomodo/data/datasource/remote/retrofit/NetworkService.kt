package com.ssafy.howdomodo.data.datasource.remote.retrofit

import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.*
import retrofit2.Call
import retrofit2.http.*


interface NetworkService {
    @POST("/users/login")
    fun login(
        @Body loginRequestBody: JsonObject
    ): Call<LoginResponse>

    @POST("/users/join")
    fun signUp(
        @Body signUpRequestBody: JsonObject
    ): Call<SignUpResponse>

    // 회원 정보 조회
    @GET("/users/{userCode}")
    fun userInfo(
        @Path("userCode") userCode: Int
    ): Call<LoginResponse>

    // 회원 정보 수정
    @PUT("/users")
    fun userUpdate(
        @Body signUpRequestBody: JsonObject
    ): Call<SignUpResponse>

    // 회원 탈퇴
    @DELETE("/users/{userCode}")
    fun userDelete(
        @Path("userCode") userCode: Int
    ): Call<SignUpResponse>

    // 닉네임 중복 체크
    @GET("/users/check/{nickName}")
    fun userNickCheck(
        @Path("nickName") nickName: String
    ): Call<SignUpResponse>

    // 비밀번호 찾기
    @GET("/users/findPw/{userEmail}/{userName}")
    fun findPW(
        @Path("userEmail") userEmail: String,
        @Path("userName") userName: String
    ): Call<PwResponse>

    // 비밀번호 재설정을 위한 비밀번호 체크
    @GET("/users/pw/{userEmail}/{originPwd}")
    fun checkPW(
        @Path("userEmail") userEmail: String,
        @Path("originPwd") originPwd: String
    ): Call<SignUpResponse>

    // 비밀번호 재설정
    @PUT("/users/pw")
    fun updatePW(
        @Body signUpRequestBody: JsonObject
    ): Call<SignUpResponse>

    //시도, 구군별 영화관 리스트 조회
    @GET("/theaters/{siName}/{guName}/{userCode}")
    fun getTheaters(
        @Path("siName") siName:String,
        @Path("guName") guName:String,
        @Path("userCode") userCode:Int
    ): Call<GetTheatersResponse>

    @GET("/theaters/bookmark/{userCode}")
    fun favoritesInfo(
        @Path("userCode") userCode : Int
    ):Call<FavoritesResponse>

    @POST("/theaters/bookmark")
    fun favoritesAdd(
        @Body favoritesRequestBody: JsonObject
    ): Call<FavoritesResponse>

    @DELETE("/theaters/bookmark/{userCode}/{theaterId}")
    fun favoritesDelete(
        @Path("userCode") userCode: Int,
        @Path("theaterId") theaterId: Int
    ): Call<FavoritesResponse>

    @GET("/theaters")
    fun getSiDo(): Call<AreaResponse>

    @GET("/theaters/{siName}")
    fun getGuGun(
        @Path("siName") siName: String
    ): Call<AreaResponse>

    @GET("/pyapi/find_si/{siName}")
    fun getCardData(
        @Path("siName") siName: String
    ): Call<CardDataResponse>

    @GET("/pyapi/timestables/{brand}/{theaterName}/{date}/{movieTitle}")
    fun getGwanData(
        @Path("brand") brand: String,
        @Path("theaterName") theaterName: String,
        @Path("date") date: String,
        @Path("movieTitle") movieTitle: String
    ): Call<GwanResponse>

}