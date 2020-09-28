package com.ssafy.howdomodo.di

import com.ssafy.howdomodo.data.datasource.remote.MovieRemoteDataSource
import com.ssafy.howdomodo.data.datasource.remote.MovieRemoteDataSourceImpl
import com.ssafy.howdomodo.data.datasource.remote.NaverRemoteDataSource
import com.ssafy.howdomodo.data.datasource.remote.NaverRemoteDataSourceImpl
import org.koin.dsl.module

val movieRemoteDataAppModule =
    module { single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl() } }