package com.ssafy.howdomodo.data.datasource.model

data class CardDataResponse(
    val list: ArrayList<Action>
)

data class Action(
    val kinds: String,
    val totalCnt: Int,
    val down: ArrayList<Down>,
    var isClicked: Boolean = false
)

data class Down(
    val desc: String,
    val jong: String,
    val cnt: Int
)