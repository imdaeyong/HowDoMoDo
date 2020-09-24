package com.ssafy.howdomodo.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.LoginResponse
import com.ssafy.howdomodo.data.repository.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    val loginResponse = MutableLiveData<LoginResponse>()
    val getHeader = MutableLiveData<String>()
    val loginError = MutableLiveData<String>()

    fun login(userData: JsonObject) {
        loginRepository.login(userData, success = { response, header ->
            loginResponse.value = response
            getHeader.value = header
        }, fail = {
            loginError.value = it.message
        })
    }

}