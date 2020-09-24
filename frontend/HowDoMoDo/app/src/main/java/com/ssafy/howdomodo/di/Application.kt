package com.ssafy.howdomodo.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@Application)
            modules(listOf(naverRemoteDataAppModule,
                movieRemoteDataAppModule,
                    remoteDataSourceAppModule,
                    repositoryAppModule,
                    viewModelAppModule,
            ))
        }
    }
}