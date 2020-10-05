package com.ssafy.howdomodo.data.datasource.model

data class FavoritesResponse(
    val status: Int,
    val message: String,
    val data: ArrayList<Theater>?
)
