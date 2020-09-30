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

    fun userInfo(userCode: String) {
        Log.e("asd",userCode)
        mypageRepository.getInfo(
            userCode,
            success = { response ->
                mypageResponse.value = response
            }, fail = {
                Log.e("TEST1", "실패")
                errorToast.value = it.message
            })
    }
}