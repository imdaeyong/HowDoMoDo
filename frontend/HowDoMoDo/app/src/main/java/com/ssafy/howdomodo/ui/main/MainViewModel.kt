package com.ssafy.howdomodo.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.howdomodo.data.datasource.model.NaverApi
import com.ssafy.howdomodo.data.datasource.model.Posting
import com.ssafy.howdomodo.data.repository.NaverRepository

class MainViewModel (private val naverRepository: NaverRepository) : ViewModel(){

    val hasWrongChar = MutableLiveData<Unit>()
    val isEmptyKeyword = MutableLiveData<Unit>()
    val isEmptyBlogData = MutableLiveData<Unit>()
    val blogData = MutableLiveData<List<Posting>>()
    private val errorToast = MutableLiveData<Throwable>()
    val blogDataNum = MutableLiveData<Int>()

    fun getBlogData(keyword: String) {
        if (keyword.contains("@")) {
            hasWrongChar.value = Unit
            return
        }
        if (keyword.equals("")) {
            isEmptyKeyword.value = Unit
            return
        }
        naverRepository.getBlogData(keyword, success = {
            if (it.items.isEmpty()) {
                isEmptyBlogData.value = Unit
            } else {
                setBlogDataNum(it)
                blogData.value = it.items
            }
        }, fail = {
            Log.e("error is :", it.toString())
            errorToast.value = it
        })
    }

    fun setBlogDataNum(data: NaverApi) {
        blogDataNum.value = data.total
    }
}