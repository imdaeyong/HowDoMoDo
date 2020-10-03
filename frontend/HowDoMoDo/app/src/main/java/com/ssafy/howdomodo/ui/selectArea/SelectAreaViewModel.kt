package com.ssafy.howdomodo.ui.selectArea

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.howdomodo.data.datasource.model.Region
import com.ssafy.howdomodo.data.repository.SelectAreaRepository

class SelectAreaViewModel(private val selectAreaRepository: SelectAreaRepository) : ViewModel() {
    val siDoData = MutableLiveData<ArrayList<Region>>()
    val guGunData = MutableLiveData<ArrayList<Region>>()
    val siDoError = MutableLiveData<String>()
    val guGunError = MutableLiveData<String>()

    fun getSiDo() {
        selectAreaRepository.getSiDo(success = {
            siDoData.value = it.data
            Log.e("sido",siDoData.value.toString())
        }, fail = {
            siDoError.value = it.message
        })
    }

    fun getGuGun(siName: String) {
        selectAreaRepository.getGuGun(siName, success = {
            guGunData.value = it.data
        }, fail = {
            guGunError.value = it.message
        })
    }
}