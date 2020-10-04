package com.ssafy.howdomodo.data.datasource.model

class AreaResponse(
    val status: Int,
    val message: String,
    val data: ArrayList<String>
)

data class Region(
    val name: String,
    var isClicked: Boolean = false
)
