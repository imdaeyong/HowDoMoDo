package com.ssafy.howdomodo.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.ssafy.howdomodo.data.datasource.model.FavoritesResponse
import com.ssafy.howdomodo.data.repository.FavoritesRepository

class FavoritesViewModel(private val favoritesRepository: FavoritesRepository) : ViewModel() {
    val favoritesError = MutableLiveData<String>()
    val successMessage = MutableLiveData<String>()
    val favoritesResponse = MutableLiveData<FavoritesResponse>()

    fun favoritesInfo(userCode: Int) {
        favoritesRepository.favoritesInfo(
            userCode,
            success = { response ->
                favoritesResponse.value = response
            }, fail = {
                favoritesError.value = it.message
            })
    }

    fun favoritesAdd(favoritesRequestBody: JsonObject) {
        favoritesRepository.favoritesAdd(favoritesRequestBody,
            success = {
                successMessage.value = it.message
            },
            fail = {
                favoritesError.value = it.message
            })
    }

    fun favoritesDelete(userCode: Int, theaterId: Int) {
        favoritesRepository.favoritesDelete(userCode, theaterId,
            success = {
                successMessage.value = it.message
            },
            fail = {
                favoritesError.value = it.message
            })
    }
}