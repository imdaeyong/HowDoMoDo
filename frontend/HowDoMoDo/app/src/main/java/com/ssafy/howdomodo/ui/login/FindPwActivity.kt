package com.ssafy.howdomodo.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssafy.howdomodo.R
import kotlinx.android.synthetic.main.activity_find_pw.*

class FindPwActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_pw)

        var email = act_find_pw_et_id
        var name = act_find_pw_et_name

    }
}