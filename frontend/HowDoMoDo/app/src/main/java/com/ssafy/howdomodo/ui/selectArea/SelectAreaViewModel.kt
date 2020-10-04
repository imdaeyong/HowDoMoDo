package com.ssafy.howdomodo.ui.selectArea

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.howdomodo.data.datasource.model.Action
import com.ssafy.howdomodo.data.datasource.model.Region
import com.ssafy.howdomodo.data.repository.SelectAreaRepository

class SelectAreaViewModel(private val selectAreaRepository: SelectAreaRepository) : ViewModel() {
    val siDoData = MutableLiveData<ArrayList<Region>>()
    val guGunData = MutableLiveData<ArrayList<Region>>()
    val cardData = MutableLiveData<ArrayList<Action>>()
    val siDoError = MutableLiveData<String>()
    val guGunError = MutableLiveData<String>()
    val cardError = MutableLiveData<String>()

    fun getSiDo() {
        selectAreaRepository.getSiDo(success = {
            val list = it.data
            val temp = ArrayList<Region>()
            for (i in list) {
                temp.add(Region(i))
            }
            siDoData.value = temp
            Log.e("sido", siDoData.value.toString())
        }, fail = {
            siDoError.value = it.message
            Log.e("asd", it.message.toString())
        })
    }

    fun getGuGun(siName: String) {
        selectAreaRepository.getGuGun(siName, success = {
            val list = it.data
            val temp = ArrayList<Region>()
            for (i in list) {
                temp.add(Region(i))
            }
            guGunData.value = temp
        }, fail = {
            guGunError.value = it.message
        })
    }

    fun getCardData(siName: String) {
        selectAreaRepository.getCardData(siName, success = {
            cardData.value = it.list
        },
            fail = {
                cardError.value = it.message
            })
    }
}