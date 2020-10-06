package com.ssafy.howdomodo.ui.theater

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.howdomodo.data.datasource.model.GetTheatersResponse
import com.ssafy.howdomodo.data.repository.GetTheatersRepository

class GetTheatersViewModel(private val getTheatersRepository: GetTheatersRepository) : ViewModel() {
    val getTheatersResponse = MutableLiveData<GetTheatersResponse>()
    val getTheatersError = MutableLiveData<String>()

    fun getTheaters(siName: String, guName: String,userCode:Int) {
        getTheatersRepository.getTheaters(siName, guName,userCode, success = { response ->
            getTheatersResponse.value = response

//            //Log.e("getTheaterTest", "getTheaters성공!")
        },
            fail = {
//                //Log.e("getTheaterTest", "영화관 불러오는데 실패했어요 ㅠ")
                getTheatersError.value = it.message
            })
    }
}
