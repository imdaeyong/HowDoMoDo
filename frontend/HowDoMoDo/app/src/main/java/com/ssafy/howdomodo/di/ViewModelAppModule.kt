package com.ssafy.howdomodo.di

import com.ssafy.howdomodo.ui.gwanSelect.GwanSelectViewModel
import com.ssafy.howdomodo.ui.login.LoginViewModel
import com.ssafy.howdomodo.ui.main.MainViewModel
import com.ssafy.howdomodo.ui.main.MovieViewModel
import com.ssafy.howdomodo.ui.mypage.MyPageViewModel
import com.ssafy.howdomodo.ui.signup.SignUpViewModel
import com.ssafy.howdomodo.ui.theater.GetTheatersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelAppModule = module {
    viewModel { SignUpViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { GwanSelectViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { MovieViewModel(get()) }
    viewModel { MyPageViewModel(get()) }
    viewModel { GetTheatersViewModel(get()) }
}