package com.ssafy.howdomodo.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.repository.SignUpRepository

class SignUpViewModel(private val signUpRepository: SignUpRepository):ViewModel(){
    val token = MutableLiveData<String>()
    val signUpError = MutableLiveData<String>()


    fun signUp(userName: JsonObject) {
        signUpRepository.signUp(userName, success = {
            token.value = it
        }, fail = {
            signUpError.value = it.message
        })

    }
}