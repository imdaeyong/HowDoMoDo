package com.ssafy.howdomodo.ui.mypage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.LoginResponse
import com.ssafy.howdomodo.data.repository.MyPageRepository
import com.ssafy.howdomodo.data.repository.SignUpRepository

class MyPageViewModel(private val mypageRepository: MyPageRepository) : ViewModel() {
    val errorToast = MutableLiveData<String>()
    val successMessage = MutableLiveData<String>()
    val mypageResponse = MutableLiveData<LoginResponse>()

    fun userInfo(userCode: Int) {
        mypageRepository.getInfo(
            userCode,
            success = { response ->
                mypageResponse.value = response
            }, fail = {
                errorToast.value = it.message
            })
    }

    fun userUpdate(jsonObject: JsonObject) {
        mypageRepository.userUpdate(jsonObject,
            success = {
                successMessage.value = it.message
            },
            fail = {
                errorToast.value = it.message
            })
    }

    fun userDelete(userCode: Int) {
        mypageRepository.userDelete(userCode,
            success = {
                successMessage.value = it.message
            },
            fail = {
                errorToast.value = it.message
            })
    }
}