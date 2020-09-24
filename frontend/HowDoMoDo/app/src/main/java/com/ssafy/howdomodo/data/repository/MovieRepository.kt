package com.ssafy.howdomodo.data.repository

import com.ssafy.howdomodo.data.datasource.model.MovieApi
import com.ssafy.howdomodo.data.datasource.model.NaverApi
import com.ssafy.howdomodo.data.datasource.remote.MovieRemoteDataSource
import com.ssafy.howdomodo.data.datasource.remote.NaverRemoteDataSource


class MovieRepository(private val movieRemoteDataSource: MovieRemoteDataSource) {

    fun getMovieData(key:String, region: String, success: (MovieApi) -> Unit, fail: (Throwable) -> Unit) {
        movieRemoteDataSource.getMovieData(key,region,success,fail)
    }
}