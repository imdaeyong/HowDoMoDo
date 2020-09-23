package com.ssafy.howdomodo.di

import com.ssafy.howdomodo.data.repository.GwanSelectRepository
import com.ssafy.howdomodo.data.repository.LoginRepository
import com.ssafy.howdomodo.data.repository.NaverRepository
import com.ssafy.howdomodo.data.repository.SignUpRepository
import org.koin.dsl.module

val repositoryAppModule = module {
    single { SignUpRepository(get()) }
    single { LoginRepository(get()) }
    single { GwanSelectRepository(get()) }
    single { NaverRepository(get()) }
}