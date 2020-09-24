package com.ssafy.howdomodo.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.howdomodo.`object`.ObjectCollection
import com.ssafy.howdomodo.data.datasource.model.Movie
import com.ssafy.howdomodo.data.repository.MovieRepository

class MovieViewModel (private val movieRepository: MovieRepository) : ViewModel(){

    val isEmptyMovieData = MutableLiveData<Unit>()
    val movieData = MutableLiveData<List<Movie>>()
    val errorToast = MutableLiveData<Throwable>()

    fun getMovieData(){
        val key = ObjectCollection.MOVIE_API_KEY
        val region = "ko"
        Log.d("TEST",key)
        movieRepository.getMovieData(key,region,success = {
            if(it.results.isEmpty()){
                isEmptyMovieData.value = Unit
            }else{
                movieData.value = it.results
            }
        },fail = {
            Log.e("error is :", it.toString())
            errorToast.value = it
        })
    }
}