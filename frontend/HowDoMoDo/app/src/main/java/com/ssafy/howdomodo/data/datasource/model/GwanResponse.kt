package com.ssafy.howdomodo.data.datasource.model

data class GwanResponse(
    val status: Int,
    val timestables: ArrayList<TimeTable>?
)

data class TimeTable(
    val hall: String,
    val screen: String,
    val total: Int,
    val timeList: ArrayList<MovieTime>
)

data class MovieTime(
    val title: String,
    val time: String,
    val count: Int,
    var isClicked: Boolean = false
)