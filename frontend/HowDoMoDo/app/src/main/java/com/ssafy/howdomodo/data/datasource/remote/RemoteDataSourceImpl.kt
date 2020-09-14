package com.ssafy.howdomodo.data.datasource.remote

import com.ssafy.howdomodo.data.datasource.remote.retrofit.NetworkServiceImpl

class RemoteDataSourceImpl : RemoteDataSource {
    val api = NetworkServiceImpl.SERVICE

}