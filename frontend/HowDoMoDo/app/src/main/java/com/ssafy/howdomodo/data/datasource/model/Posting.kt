package com.ssafy.howdomodo.data.datasource.model

import java.time.LocalDate
import java.util.*

data class Posting(
        val movieName: String,
        val title: String,
        val description: String,
        val postdate : LocalDate,
        var bloggerlink: String
)