package com.ssafy.howdomodo.data.datasource.model

data class StoreResponse(
    val list: ArrayList<Store>
)

data class Store(
    val kinds: String,
    val totalCnt: Int,
    val down: ArrayList<StoreDetail>,
    var isClicked: Boolean = false
)

data class StoreDetail(
    val desc: String,
    val cate: String,
    val cnt: Int
)