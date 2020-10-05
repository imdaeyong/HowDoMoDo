package com.ssafy.howdomodo.ui.mypage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.`object`.UserCollection
import com.ssafy.howdomodo.ui.bottomtap.BottomTabActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_mypage.*
import kotlinx.android.synthetic.main.activity_update_pw.*
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdatePwActivity : AppCompatActivity() {
    private val mypageViewModel: MyPageViewModel by viewModel()
    var checkBoolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_pw)

        act_update_pw_btn.setOnClickListener{
            var email = UserCollection.userEmail
            var bpwd = act_update_pw_et_before.text.toString()
            var apwd = act_update_pw_et_after.text.toString()
            mypageViewModel.checkPW(email,bpwd)
            observe()

            if(checkBoolean){
                val loginJsonObject = JSONObject()
                loginJsonObject.put("userEmail", email)
                loginJsonObject.put("userPw", apwd)
                val body = JsonParser.parseString(loginJsonObject.toString()) as JsonObject
                mypageViewModel.updatePW(body)
                observe()
            }else{
                Toast.makeText(it.context,"이전 비밀번호를 확인하세요",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun observe(){
        mypageViewModel.checkMessage.observe(this, Observer {
            Log.e("TEST",it)
            if(it == "인증 성공"){
                checkBoolean = true
            }
        })
        mypageViewModel.successMessage.observe(this, Observer {
            Log.e("TEST",it)
            if(it == "비밀번호 재설정 성공"){
                Toast.makeText(this,it,Toast.LENGTH_LONG).show()
                val intent = Intent(this, MypageActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }
}