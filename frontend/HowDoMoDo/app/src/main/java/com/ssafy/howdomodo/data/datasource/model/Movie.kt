package com.ssafy.howdomodo.data.datasource.model

data class Movie(
        val title: String,
        val genreIds: String,
        val age:String,
        val voteAverage: Double,
        var posterPath: String,
        var id: Int
)