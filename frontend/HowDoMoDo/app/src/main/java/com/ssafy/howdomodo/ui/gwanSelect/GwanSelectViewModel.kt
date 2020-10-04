package com.ssafy.howdomodo.ui.gwanSelect

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.howdomodo.data.datasource.model.TimeTable
import com.ssafy.howdomodo.data.repository.GwanSelectRepository

class GwanSelectViewModel(private val gwanSelectRepository: GwanSelectRepository) : ViewModel() {
    val gwanData = MutableLiveData<ArrayList<TimeTable>>()
    val gwanError = MutableLiveData<String>()
    val gwanNull = MutableLiveData<Unit>()

    fun getGwanData(
        brand: String,
        theaterName: String,
        date: String,
        movieTitle: String
    ) {
        gwanSelectRepository.getGwanData(brand, theaterName, date, movieTitle,
            success = {
                gwanData.value = it.timestables
            },
            fail = {
                gwanError.value = it.message
            },
            ifNull = {
                gwanNull.value = Unit
            })
    }
}