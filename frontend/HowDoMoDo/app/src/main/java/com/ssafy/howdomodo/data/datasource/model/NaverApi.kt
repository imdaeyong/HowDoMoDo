package com.ssafy.howdomodo.data.datasource.model
import java.time.format.DateTimeFormatter
import java.util.*


data class NaverApi (
    val total : Int,
    val postdate :  String,
    val items: List<Posting>
)
