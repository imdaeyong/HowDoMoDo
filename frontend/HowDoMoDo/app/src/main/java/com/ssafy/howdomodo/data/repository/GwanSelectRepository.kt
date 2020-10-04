package com.ssafy.howdomodo.data.repository

import com.ssafy.howdomodo.data.datasource.model.GwanResponse
import com.ssafy.howdomodo.data.datasource.remote.RemoteDataSource

class GwanSelectRepository(private val remoteDataSource: RemoteDataSource) {
    fun getGwanData(
        brand: String,
        theaterName: String,
        date: String,
        movieTitle: String,
        success: (GwanResponse) -> Unit,
        fail: (Throwable) -> Unit,
        ifNull: () -> Unit
    ) = remoteDataSource.getGwanData(brand, theaterName, date, movieTitle, success, fail, ifNull)
}