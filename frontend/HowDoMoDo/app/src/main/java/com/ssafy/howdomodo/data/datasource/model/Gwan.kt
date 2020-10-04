package com.ssafy.howdomodo.data.datasource.model

data class Gwan(
        val kind: String,
        val number: Int,
        val seatCount: Int,
        val times: ArrayList<MovieTime>
)

