package com.ssafy.howdomodo.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.LoginResponse
import com.ssafy.howdomodo.data.repository.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    val loginResponse = MutableLiveData<LoginResponse>()
    val tempPw = MutableLiveData<String>()
    val getHeader = MutableLiveData<String>()
    val loginError = MutableLiveData<String>()
    val findError = MutableLiveData<String>()

    fun login(loginRequestBody: JsonObject) {
        Log.e("TEST1",loginRequestBody.get("userPw").toString())
        loginRepository.login(loginRequestBody, success = { response ->
            loginResponse.value = response
        }, fail = {
            Log.e("TEST1","실패")
            loginError.value = it.message
        })
    }

    fun findPW(userEmail: String, userName: String) {
        loginRepository.findPW(userEmail,userName,success = {
            tempPw.value = it.data.toString()
        }, fail = {
            findError.value = it.message
        })
    }

}