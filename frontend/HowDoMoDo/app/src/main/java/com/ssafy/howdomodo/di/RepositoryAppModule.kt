package com.ssafy.howdomodo.di

import com.ssafy.howdomodo.data.repository.*
import org.koin.dsl.module

val repositoryAppModule = module {
    single { SignUpRepository(get()) }
    single { LoginRepository(get()) }
    single { GwanSelectRepository(get()) }
    single { NaverRepository(get()) }
    single { MovieRepository(get()) }
    single { MyPageRepository(get()) }
    single { GetTheatersRepository(get()) }
    single { FavoritesRepository(get()) }
    single { SelectAreaRepository(get()) }
}