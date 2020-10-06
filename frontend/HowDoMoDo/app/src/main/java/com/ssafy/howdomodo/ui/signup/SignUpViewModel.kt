package com.ssafy.howdomodo.ui.signup

import android.util.Log
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

    fun userEmailCheck(email: String) {
        signUpRepository.userEmailCheck(email,
            success = {
                //Log.e("userEmailCheck","success"+successMessage.value)
                successMessage.value = it.message
            },
            fail = {
                //Log.e("userEmailCheck","fail"+email)

                errorToast.value = it.message
            })
    }

    fun userNickCheck(nickname: String) {
        signUpRepository.userNickCheck(nickname,
            success = {
                //Log.e("userNickCheck","success"+successMessage.value)
                successMessage.value = it.message
            },
            fail = {
                //Log.e("userNickCheck","fail"+nickname)
                errorToast.value = it.message
            })
    }
}