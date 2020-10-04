package com.ssafy.howdomodo.data.repository

import com.ssafy.howdomodo.data.datasource.model.GetTheatersResponse
import com.ssafy.howdomodo.data.datasource.remote.RemoteDataSource

class GetTheatersRepository(private val remoteDataSource: RemoteDataSource) {
    //    fun getTheaters(
//        getTheatersRequestBody: JsonObject,
//        success: (GetTheatersResponse) -> Unit,
//        fail: (Throwable) -> Unit
//    ) = remoteDataSource.getTheaters(getTheatersRequestBody, success, fail)
    fun getTheaters(
        siName: String,
        guName: String,
        userCode :Int,
        success: (GetTheatersResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        remoteDataSource.getTheaters(siName, guName,userCode, success, fail)
    }

}
