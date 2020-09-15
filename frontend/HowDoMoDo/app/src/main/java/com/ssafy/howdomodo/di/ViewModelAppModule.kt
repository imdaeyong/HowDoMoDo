package com.ssafy.howdomodo.di

import com.ssafy.howdomodo.ui.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelAppModule = module {
    viewModel { SignUpViewModel(get()) }
}