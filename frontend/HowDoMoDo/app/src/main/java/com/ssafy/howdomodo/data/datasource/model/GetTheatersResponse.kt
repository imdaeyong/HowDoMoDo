package com.ssafy.howdomodo.data.datasource.model

data class GetTheatersResponse(
    val status: Int,
    val message: String,
    val data: ArrayList<Theater>?
)
