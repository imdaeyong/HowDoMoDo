package com.ssafy.howdomodo.data.datasource.model

data class LoginResponse(
        val status: Int,
        val message: String,
        val data: Users?
)

data class Users(
        val userCode: String,
        val userEmail: String,
        val userName: String,
        val userNick: String,
        val userPw: String,
        val userGender: Int,
        val userBirth: String
)