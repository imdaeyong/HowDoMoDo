package com.ssafy.howdomodo.ui.main;

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.ui.bottomtap.BottomTabActivity
import com.ssafy.howdomodo.ui.gwanSelect.GwanSelectActivity
import com.ssafy.howdomodo.ui.login.LoginActivity
import com.ssafy.howdomodo.ui.selectArea.SelectAreaActivity
import com.ssafy.howdomodo.ui.signup.SignupActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.security.MessageDigest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        act_main_btn_login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        act_main_btn_signup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        act_main_btn_bottom.setOnClickListener {
            val intent = Intent(this, BottomTabActivity::class.java)
            startActivity(intent)
        }
        act_main_btn_gwan.setOnClickListener {
            val intent = Intent(this, GwanSelectActivity::class.java)
            startActivity(intent)
        }
        act_main_btn_select_area.setOnClickListener {
            val intent = Intent(this, SelectAreaActivity::class.java)
            startActivity(intent)
        }
        getAppKeyHash()
    }
    private fun getAppKeyHash() {
        try {
            val info =
                packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                var md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val something = String(Base64.encode(md.digest(), 0))
                Log.e("Hash key", something)
            }
        } catch (e: Exception) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString())
        }
    }
}