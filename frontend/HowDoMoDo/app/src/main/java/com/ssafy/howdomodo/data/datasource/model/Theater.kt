package com.ssafy.howdomodo.data.datasource.model

class Theater(
        val theaterId :Int,
        val cityId : Int,
        val theaterName : String,
        var theaterAddress : String,
        val theaterBrand : String,
        var theaterLat : Double,
        var theaterLng : Double,
        var isClicked :Boolean =false
)