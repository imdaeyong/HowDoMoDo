package com.ssafy.howdomodo.ui.main;

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.ui.bottomtap.BottomTabActivity
import com.ssafy.howdomodo.ui.gwanSelect.GwanSelectActivity
import com.ssafy.howdomodo.ui.login.LoginActivity
import com.ssafy.howdomodo.ui.selectArea.SelectAreaActivity
import com.ssafy.howdomodo.ui.signup.SignupActivity
import kotlinx.android.synthetic.main.activity_main.*

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
    }

}