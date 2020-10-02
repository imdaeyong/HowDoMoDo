package com.ssafy.howdomodo.data.datasource.model

data class Movie(
        val title: String,
        val genre_ids: List<Integer>,
        val vote_average: Double,
        var poster_path: String,
        var id: Int
)