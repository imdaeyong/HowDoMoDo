package com.ssafy.howdomodo.data.datasource.model

data class LoginResponse(
        val status: Int,
        val message: String,
        val data: Users
)

data class Users(
        val email: String,
        val password: String
)