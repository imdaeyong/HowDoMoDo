package com.ssafy.howdomodo.data.datasource.remote

import com.ssafy.howdomodo.data.datasource.model.BigDataPsNs
import com.ssafy.howdomodo.data.datasource.model.MovieResponse

interface MovieRemoteDataSource {
    fun getMovieData(
        key: String,
        region: String,
        onResponse: (MovieResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun getMoviePsNs(
        code: String,
        onResponse: (BigDataPsNs) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun getNewMovieData(success: (MovieResponse) -> Unit, fail: (Throwable) -> Unit)
}