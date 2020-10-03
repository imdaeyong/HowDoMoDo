package com.ssafy.howdomodo.data.datasource.model

class AreaResponse(
    val status: Int,
    val message: String,
    val data: ArrayList<Region>
)

data class Region(
    val siName: String,
    var isClicked: Boolean = false
)

data class City(
    val cityId: Int,
    val guName: String,
    var isClicked: Boolean = false
)