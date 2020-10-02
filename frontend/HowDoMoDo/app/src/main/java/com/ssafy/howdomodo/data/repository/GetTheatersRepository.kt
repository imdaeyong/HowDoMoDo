package com.ssafy.howdomodo.data.repository

import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.GetTheatersResponse
import com.ssafy.howdomodo.data.datasource.model.LoginResponse
import com.ssafy.howdomodo.data.datasource.model.SignUpResponse
import com.ssafy.howdomodo.data.datasource.remote.RemoteDataSource

class GetTheatersRepository(private val remoteDataSource: RemoteDataSource){
//    fun getTheaters(
//        getTheatersRequestBody: JsonObject,
//        success: (GetTheatersResponse) -> Unit,
//        fail: (Throwable) -> Unit
//    ) = remoteDataSource.getTheaters(getTheatersRequestBody, success, fail)
fun getTheaters(siName:String, guName:String, success: (GetTheatersResponse) -> Unit, fail: (Throwable) -> Unit) {
    remoteDataSource.getTheaters(siName,guName,success,fail)
}

}
