package com.ssafy.howdomodo.data.datasource.model
import java.time.format.DateTimeFormatter
import java.util.*


data class MovieResponse (
    val status :Int,
    val movie : List<Movie>
)
