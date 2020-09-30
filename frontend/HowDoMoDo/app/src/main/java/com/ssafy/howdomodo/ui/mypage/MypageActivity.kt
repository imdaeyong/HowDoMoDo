package com.ssafy.howdomodo.ui.mypage

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Users
import kotlinx.android.synthetic.main.activity_mypage.*

class MypageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)
        var info = intent.getParcelableExtra<Users>("info")
        if (info != null) {
            act_mypage_update_et_nick.setText(info.userNick)
            act_mypage_update_et_name.setText(info.userName)
            act_mypage_update_et_birth.setText(info.userBirth)
            if(info.userGender==1){
                act_mypage_update_cl_gender_male.setBackgroundResource(R.drawable.item_sido_selected)
                act_mypage_update_tv_gender_male.setTextColor(Color.parseColor("#fff4eb"))
            }else{
                act_mypage_update_cl_gender_female.setBackgroundResource(R.drawable.item_sido_selected)
                act_mypage_update_tv_gender_female.setTextColor(Color.parseColor("#fff4eb"))
            }
        }
    }
}