package com.ssafy.howdomodo.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.ssafy.howdomodo.R
import kotlinx.android.synthetic.main.activity_find_pw.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_login.*

class FindPwActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_pw)
        observe()

        act_find_pw_btn.setOnClickListener{
            var email = act_find_pw_et_id.text.toString()
            var name = act_find_pw_et_name.text.toString()
            Log.e("TEST1",email+" "+name)
            loginViewModel.findPW(email,name)
        }
    }
    private fun observe() {
        loginViewModel.tempPw.observe(this, Observer {
            Log.e("Final","??")
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("임시 비밀번호")
            dialog.setMessage(it)
            dialog.show()
        })
    }
}