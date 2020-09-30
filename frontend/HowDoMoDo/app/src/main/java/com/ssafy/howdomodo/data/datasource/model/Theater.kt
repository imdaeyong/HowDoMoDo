package com.ssafy.howdomodo.data.datasource.model

class Theater(
        val kind : String,
        val name : String,
        val distance : String,
        var isClicked : Boolean,
        var theater_lat : Double,
        var theater_lng : Double,
        var isSelectedMarker :Boolean,
)