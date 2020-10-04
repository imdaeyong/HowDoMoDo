package com.ssafy.howdomodo.data.repository

import com.ssafy.howdomodo.data.datasource.model.AreaResponse
import com.ssafy.howdomodo.data.datasource.model.CardDataResponse
import com.ssafy.howdomodo.data.datasource.remote.RemoteDataSource

class SelectAreaRepository(private val remoteDataSource: RemoteDataSource) {
    fun getSiDo(success: (AreaResponse) -> Unit, fail: (Throwable) -> Unit) =
        remoteDataSource.getSiDo(success, fail)

    fun getGuGun(siName: String, success: (AreaResponse) -> Unit, fail: (Throwable) -> Unit) =
        remoteDataSource.getGuGun(siName, success, fail)

    fun getCardData(
        siName: String,
        success: (CardDataResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) =
        remoteDataSource.getCardData(siName, success, fail)
}