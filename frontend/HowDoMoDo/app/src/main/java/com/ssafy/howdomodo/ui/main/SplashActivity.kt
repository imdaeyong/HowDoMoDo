package com.ssafy.howdomodo.ui.main

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.`object`.UserCollection
import com.ssafy.howdomodo.ui.bottomtap.BottomTabActivity
import com.ssafy.howdomodo.ui.login.LoginActivity


class SplashActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreferences = getSharedPreferences("TOKEN", MODE_PRIVATE)
        UserCollection.userEmail = sharedPreferences.getString("userEmail", "").toString()
        if (UserCollection.userEmail == "") {
            // 자동로그인 안했을떄
            startLoading(LoginActivity())
        } else {
            UserCollection.userEmail = sharedPreferences.getString("userEmail", "").toString()
            UserCollection.userName = sharedPreferences.getString("userName", "").toString()
            UserCollection.userNick = sharedPreferences.getString("userNick", "").toString()
            UserCollection.userCode = sharedPreferences.getString("userCode", "").toString()
            startLoading(BottomTabActivity())
        }

    }

    private fun startLoading(activity: Activity) {
        val handler = Handler()
        handler.postDelayed(Runnable {
            val intent = Intent(baseContext, activity::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }
}