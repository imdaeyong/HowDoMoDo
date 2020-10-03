package com.ssafy.howdomodo.data.datasource.remote

import com.ssafy.howdomodo.data.datasource.model.BigDataPsNs
import com.ssafy.howdomodo.data.datasource.model.MovieApi

interface MovieRemoteDataSource {
    fun getMovieData(key:String, region: String, onResponse: (MovieApi) -> Unit, onFailure: (Throwable) -> Unit)

    fun getMoviePsNs(title:String, onResponse: (BigDataPsNs) -> Unit, onFailure: (Throwable) -> Unit)
}