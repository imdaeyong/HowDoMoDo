package com.ssafy.howdomodo.data.repository

import com.ssafy.howdomodo.data.datasource.model.BigDataPsNs
import com.ssafy.howdomodo.data.datasource.model.MovieResponse
import com.ssafy.howdomodo.data.datasource.remote.MovieRemoteDataSource


class MovieRepository(private val movieRemoteDataSource: MovieRemoteDataSource) {

    fun getMovieData(
        key: String,
        region: String,
        success: (MovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        movieRemoteDataSource.getMovieData(key, region, success, fail)
    }

    fun getMoviePsNs(code: String, success: (BigDataPsNs) -> Unit, fail: (Throwable) -> Unit) {
        movieRemoteDataSource.getMoviePsNs(code, success, fail)
    }

    fun getNewMovie(success: (MovieResponse) -> Unit, fail: (Throwable) -> Unit) {
        movieRemoteDataSource.getNewMovieData(success, fail)
    }
}