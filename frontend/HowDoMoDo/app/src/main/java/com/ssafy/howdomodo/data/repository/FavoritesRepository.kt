package com.ssafy.howdomodo.data.repository

import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.*
import com.ssafy.howdomodo.data.datasource.remote.RemoteDataSource

class FavoritesRepository(private val remoteDataSource: RemoteDataSource) {
    fun favoritesInfo(userCode:Int, success: (FavoritesResponse) -> Unit, fail: (Throwable) -> Unit) {
        remoteDataSource.favoritesInfo(userCode,success,fail)
    }
    fun favoritesAdd(
        favoritesRequestBody: JsonObject,
        success: (FavoritesResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) = remoteDataSource.favoritesAdd(favoritesRequestBody, success, fail)

    fun favoritesDelete(
        userCode: Int,
        theaterId : Int,
        success: (FavoritesResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) = remoteDataSource.favoritesDelete(userCode,theaterId, success, fail)


}
