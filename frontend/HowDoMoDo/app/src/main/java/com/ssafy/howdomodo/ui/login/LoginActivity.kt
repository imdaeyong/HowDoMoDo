package com.ssafy.howdomodo.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val nameViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Login_Control().edit_init()

        observeData()
    }

    private fun observeData() {
        nameViewModel.loginError.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
        nameViewModel.getHeader.observe(this, Observer {
            // TODO: 2020/09/16 헤더를 넣어주는 로직을 짜야한다. SharedPreferences 를 사용하라.
            val token = it
            if(act_login_cb_auto_login.isChecked){
                val sf = getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
                val editor = sf.edit()
                editor.putString("userToken",token)
                editor.commit()
            }
        })
        nameViewModel.loginResponse.observe(this, Observer {
            // TODO: 2020/09/16 로그인 통신이 성공했을때의 로직을 짜라.
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", it.data.email)
            startActivity(intent)
            finish()

        })

    }

    inner class Login_Control {
        fun edit_init() {
            act_login_et_id.addTextChangedListener(EditListener())
            act_login_et_password.addTextChangedListener(EditListener())
        }

        fun edit_check(): Boolean {
            if (act_login_et_id.text.isNullOrEmpty() && act_login_et_password.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
                return false
            }
            if (act_login_et_id.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "아이디를 입력해주세요", Toast.LENGTH_LONG).show()
                return false
            }
            if (act_login_et_password.text.isNullOrEmpty()) {
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
                    Toast.makeText(applicationContext, act_login_et_id.text.toString() + " , " + act_login_et_password.text.toString(), Toast.LENGTH_LONG).show()

                    val loginJsonObject = JSONObject()
                    loginJsonObject.put("userName", act_login_et_id.text.toString())
                    loginJsonObject.put("userPassword", act_login_et_password.text.toString())
                    val body = JsonParser.parseString(loginJsonObject.toString()) as JsonObject
                    nameViewModel.login(body)
//                    Login_Control().POST_login(act_login_et_id.text.toString(), act_login_et_password.text.toString())
                }
            }
//            R.id.act_login_tv_sign_up ->startActivity(Intent(applicationContext, SignUpActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
        }
    }

    fun Findpw_Click_Listener(view :View){

    }

    fun Signup_Click_Listener(view :View){
        val goSignUpActivity = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(goSignUpActivity)

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
