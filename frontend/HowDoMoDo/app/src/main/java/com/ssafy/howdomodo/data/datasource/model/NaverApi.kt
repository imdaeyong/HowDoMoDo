package com.ssafy.howdomodo.data.datasource.model
import java.util.*


data class NaverApi (
    val total : Int,
    val postdate :  Date,
    val items: List<Posting>
)
