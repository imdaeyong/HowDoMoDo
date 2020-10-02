package com.ssafy.howdomodo.ui.theater

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.GetTheatersResponse
import com.ssafy.howdomodo.data.datasource.model.LoginResponse
import com.ssafy.howdomodo.data.repository.GetTheatersRepository
import com.ssafy.howdomodo.data.repository.LoginRepository
import com.ssafy.howdomodo.data.repository.SignUpRepository

class GetTheatersViewModel(private val getTheatersRepository: GetTheatersRepository) : ViewModel() {
    val getTheatersResponse = MutableLiveData<GetTheatersResponse>()
    val getTheatersError = MutableLiveData<String>()

    fun getTheaters(getTheatersRequestBody: JsonObject) {
        Log.e("getTheaterTest",getTheatersRequestBody.get("userCode").toString())
        getTheatersRepository.getTheaters(getTheatersRequestBody, success = { response ->
                getTheatersResponse.value = response
            },
            fail = {
                Log.e("getTheaterTest","영화관 불러오는데 실패했어요 ㅠ")

                getTheatersError.value = it.message
            })
    }
}
