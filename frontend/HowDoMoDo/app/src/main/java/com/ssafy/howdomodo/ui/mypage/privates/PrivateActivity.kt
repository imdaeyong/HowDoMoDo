package com.ssafy.howdomodo.ui.mypage.privates

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.howdomodo.R
import kotlinx.android.synthetic.main.activity_private.*
import kotlinx.android.synthetic.main.mypage_top.view.*

class PrivateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_private)

        val title = intent.getStringExtra("title")
        val text = intent.getStringExtra("text")
        //Log.e("we",text.toString())
        act_private_top.my_page_top_tv_title.text = title
        act_private_tv_text.text = text

        act_private_top.my_page_top_iv_back.setOnClickListener {
            finish()
        }


    }
}