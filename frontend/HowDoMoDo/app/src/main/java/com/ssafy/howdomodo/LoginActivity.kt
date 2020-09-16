package com.ssafy.howdomodo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Login_Control().edit_init()
    }

    inner class Login_Control {
        fun edit_init(){
            act_login_et_id.addTextChangedListener(EditListener())
            act_login_et_password.addTextChangedListener(EditListener())
        }
        fun edit_check() : Boolean{
            if(act_login_et_id.text.isNullOrEmpty() && act_login_et_password.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
                return false
            }
            if (act_login_et_id.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "아이디를 입력해주세요", Toast.LENGTH_LONG).show()
                return false
            }
            if (act_login_et_password.text.isNullOrEmpty()){
                Toast.makeText(applicationContext, "비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
                return false
            }
            return true
        }
    }

    fun Login_Click_Listener(view :View){
        when(view.id){
            R.id.act_login_btn ->{
                if(Login_Control().edit_check()) {
                    Toast.makeText(applicationContext, act_login_et_id.text.toString()+" , "+act_login_et_password.text.toString(), Toast.LENGTH_LONG).show()
//                    Login_Control().POST_login(act_login_et_id.text.toString(), act_login_et_password.text.toString())
                }
            }
//            R.id.act_login_tv_sign_up ->startActivity(Intent(applicationContext, SignUpActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
        }
    }
    inner class EditListener : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if(!s.isNullOrEmpty())
                act_login_btn.isEnabled = !act_login_et_id.text.isNullOrEmpty() && !act_login_et_password.text.isNullOrEmpty()
            else
                act_login_btn.isEnabled = false
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {       }
    }

}
