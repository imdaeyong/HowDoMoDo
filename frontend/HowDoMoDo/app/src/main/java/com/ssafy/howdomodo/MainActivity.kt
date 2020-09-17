package com.ssafy.howdomodo;

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val sf = getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
        val token = sf.getString("token","")
        if(token.equals("")){
            
        }else{
            // 통신코드
        }

    }

}