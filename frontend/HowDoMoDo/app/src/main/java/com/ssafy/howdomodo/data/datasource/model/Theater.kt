package com.ssafy.howdomodo.data.datasource.model

import android.os.Parcel
import android.os.Parcelable

class Theater(
        val theaterId :Int,
        val cityId : Int,
        val theaterName : String,
        var theaterAddress : String,
        val theaterBrand : String,
        var theaterLat : Double,
        var theaterLon : Double,
        var isClicked :Boolean =false,
        var fav : Boolean
)
