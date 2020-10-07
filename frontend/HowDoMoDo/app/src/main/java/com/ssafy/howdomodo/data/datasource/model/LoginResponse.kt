package com.ssafy.howdomodo.data.datasource.model

import android.os.Parcelable

data class LoginResponse(
        val status: Int,
        val message: String,
        val data: Users?
)



