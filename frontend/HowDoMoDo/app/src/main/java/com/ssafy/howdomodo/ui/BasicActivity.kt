package com.ssafy.howdomodo.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity


open class BasicActivity : AppCompatActivity() {

    companion object {
    val actList = ArrayList<Activity>()
}
    fun actFinish() {
        for (i in 0 until actList.size) {
            actList[i].finish()
        }
    }

}

