package com.ssafy.howdomodo.ui.main

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.howdomodo.data.datasource.model.NaverApi
import com.ssafy.howdomodo.data.datasource.model.Posting
import com.ssafy.howdomodo.data.repository.NaverRepository

class MainViewModel (private val naverRepository: NaverRepository) : ViewModel(){

    val isEmptyBlogData = MutableLiveData<Unit>()
    val blogData = MutableLiveData<List<Posting>>()
    val errorToast = MutableLiveData<Throwable>()

    fun getBlogData(keyword: String) {

        naverRepository.getBlogData(keyword, success = {
            if (it.items.isEmpty()) {
                isEmptyBlogData.value = Unit
            } else {
                blogData.value = it.items
            }
        }, fail = {
            Log.e("error is :", it.toString())
            errorToast.value = it
        })
    }
}