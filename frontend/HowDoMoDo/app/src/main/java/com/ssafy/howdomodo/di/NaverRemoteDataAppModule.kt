package com.ssafy.howdomodo.di

import com.ssafy.howdomodo.data.datasource.remote.NaverRemoteDataSource
import com.ssafy.howdomodo.data.datasource.remote.NaverRemoteDataSourceImpl
import org.koin.dsl.module

val naverRemoteDataAppModule =
    module { single<NaverRemoteDataSource> { NaverRemoteDataSourceImpl() } }