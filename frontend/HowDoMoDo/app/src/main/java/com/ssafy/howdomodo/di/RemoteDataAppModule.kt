package com.ssafy.howdomodo.di

import com.ssafy.howdomodo.data.datasource.remote.RemoteDataSource
import com.ssafy.howdomodo.data.datasource.remote.RemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataSourceAppModule = module { single<RemoteDataSource> { RemoteDataSourceImpl() } }