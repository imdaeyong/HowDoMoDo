package com.ssafy.howdomodo.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.repository.SignUpRepository

class SignUpViewModel(private val signUpRepository: SignUpRepository) : ViewModel() {
    val errorToast = MutableLiveData<String>()
    val successMessage = MutableLiveData<String>()

    fun signUp(signUpRequestBody: JsonObject) {
        signUpRepository.signUp(signUpRequestBody,
            success = {
                successMessage.value = it.message
            },
            fail = {
                errorToast.value = it.message
            })
    }
}