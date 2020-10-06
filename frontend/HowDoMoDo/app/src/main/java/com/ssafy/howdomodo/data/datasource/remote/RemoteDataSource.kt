package com.ssafy.howdomodo.data.datasource.remote

import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.*


interface RemoteDataSource {
    fun login(
        loginRequestBody: JsonObject,
        success: (LoginResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun signUp(
        signUpRequestBody: JsonObject,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun userInfo(
        userCode:Int,
        success: (LoginResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun userUpdate(
        signUpRequestBody: JsonObject,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun userDelete(
        userCode:Int,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun userNickCheck(
        nickname:String,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun userEmailCheck(
        email: String,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun nickDuplicateCheck(
        userNick: String,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun findPW(
        userEmail: String,
        userName: String,
        success: (PwResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun checkPW(
        userEmail: String,
        originPwd: String,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun updatePW(
        signUpRequestBody: JsonObject,
        success: (SignUpResponse) -> Unit,
        fail: (Throwable) -> Unit
    )


    fun getTheaters(
        siName:String,
        guName:String,
        userCode:Int,
        success: (GetTheatersResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun favoritesInfo(
        userCode:Int,
        success :(FavoritesResponse) ->Unit,
        fail : (Throwable) ->Unit
    )
    fun favoritesAdd(
        favoritesRequestBody:JsonObject,
        success :(FavoritesResponse) ->Unit,
        fail : (Throwable) ->Unit
    )fun favoritesDelete(
        userCode:Int,
        theaterId:Int,
        success :(FavoritesResponse) ->Unit,
        fail : (Throwable) ->Unit
    )

    fun getSiDo(
        success: (AreaResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun getGuGun(
        siName: String,
        success: (AreaResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun getCardData(
        siName: String,
        success: (CardDataResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun getGwanData(
        brand: String,
        theaterName: String,
        date: String,
        movieTitle: String,
        success: (GwanResponse) -> Unit,
        fail: (Throwable) -> Unit,
        ifNull: () -> Unit
    )
}