package com.ssafy.howdomodo.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.howdomodo.`object`.ObjectCollection
import com.ssafy.howdomodo.data.datasource.model.BigDataPsNs
import com.ssafy.howdomodo.data.datasource.model.Movie
import com.ssafy.howdomodo.data.repository.MovieRepository

class MovieViewModel (private val movieRepository: MovieRepository) : ViewModel(){

    val loading = MutableLiveData<Boolean>()
    val isEmptyMovieData = MutableLiveData<Unit>()
    val movieData = MutableLiveData<List<Movie>>()
    val spinnerData = arrayListOf<String>()
    val spinnerCopyData =MutableLiveData<ArrayList<String>>()
    val errorToast = MutableLiveData<Throwable>()
    val psnsData = MutableLiveData<BigDataPsNs>()

    fun getMovieData(){
        loading.value = true
        val key = ObjectCollection.MOVIE_API_KEY
        val region = "ko"
        Log.d("TEST", key)
        movieRepository.getMovieData(key, region, success = {
            if (it.movie.isEmpty()) {
                isEmptyMovieData.value = Unit
            } else {
                movieData.value = it.movie
                for (item in it.movie) {
                    spinnerData.add(item.title)
                }
            }
            spinnerCopyData.value = spinnerData
        }, fail = {
            Log.e("error is :", it.toString())
            errorToast.value = it
        })

        loading.value = false
    }

    fun getMoviePsNs(title: String) {
        movieRepository.getMoviePsNs(title, success = {
            psnsData.value = it
        }, fail = {
            Log.e("error is :", it.toString())
            errorToast.value = it
        })
    }

    fun getNewMoviedata() {
        loading.value = true
        movieRepository.getNewMovie(
            success = {
                if (it.movie.isEmpty()) {
                    isEmptyMovieData.value = Unit
                } else {
                    movieData.value = it.movie
                    for (item in it.movie) {
                        spinnerData.add(item.title)
                    }
                }
                spinnerCopyData.value = spinnerData

            },
            fail = {
                errorToast.value = it
            })
        loading.value = false

    }
}