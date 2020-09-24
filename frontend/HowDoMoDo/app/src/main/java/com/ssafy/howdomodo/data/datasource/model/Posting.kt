package com.ssafy.howdomodo.data.datasource.model

data class Posting(
        val movieName: String,
        val movieTitle: String,
        val movieContent: String,
        val movieDate: String,
        var moviePostingURL: String
)