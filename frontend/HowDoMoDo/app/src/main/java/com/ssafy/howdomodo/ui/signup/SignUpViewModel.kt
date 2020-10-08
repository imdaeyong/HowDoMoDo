package com.ssafy.howdomodo.ui.signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.repository.SignUpRepository

class SignUpViewModel(private val signUpRepository: SignUpRepository) : ViewModel() {
    val errorToast = MutableLiveData<String>()
    val signUpSuccessMessage = MutableLiveData<String>()
    val emailCheckSuccessMessage = MutableLiveData<String>()
    val nickCheckSuccessMessage = MutableLiveData<String>()

    fun signUp(signUpRequestBody: JsonObject) {
        signUpRepository.signUp(signUpRequestBody,
            success = {
                signUpSuccessMessage.value = it.message
            },
            fail = {
                errorToast.value = it.message
            })
    }

    fun userEmailCheck(email: String) {
        signUpRepository.userEmailCheck(email,
            success = {
                emailCheckSuccessMessage.value = it.message
                Log.e("userEmailCheck","success"+emailCheckSuccessMessage.value)

            },
            fail = {
                //Log.e("userEmailCheck","fail"+email)

                errorToast.value = it.message
            })
    }

    fun userNickCheck(nickname: String) {
        signUpRepository.userNickCheck(nickname,
            success = {
                nickCheckSuccessMessage.value = it.message
                Log.e("userNickCheck","success"+nickCheckSuccessMessage.value)

            },
            fail = {
                //Log.e("userNickCheck","fail"+nickname)
                errorToast.value = it.message
            })
    }
}