package com.ssafy.howdomodo.ui.signup

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_signup.*
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel

//import javax.mail.Message
//import javax.mail.PasswordAuthentication
//import javax.mail.Session
//import javax.mail.Transport
//import javax.mail.internet.InternetAddress
//import javax.mail.internet.MimeMessage

class SignupActivity : AppCompatActivity() {
    val signUpViewModel: SignUpViewModel by viewModel()
    var duplicateEmailCheck = false
    var duplicateNickCheck = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        observe()

        val str = intent.getStringExtra("btnName")
        Toast.makeText(this, str.toString(), Toast.LENGTH_SHORT).show()



        act_sign_up_cl_email_duplicate_check.setOnClickListener {
            var email = act_sign_up_et_email.text.toString()
            Log.e("email중복버튼클릭",email)
            if(email!=""){
                signUpViewModel.userEmailCheck(email)
//                var err = signUpViewModel.errorToast.value
//                var data = signUpViewModel.successMessage.value
//                if(err!=null) {
//                    Log.e("emailcheck", err!!)
//                    Toast.makeText(this,"오류가 발생했습니다",Toast.LENGTH_SHORT)
//                }else if(data!=null){

            }else{
                Toast.makeText(this,"이메일을 입력해주세요!",Toast.LENGTH_SHORT)
            }
        }

        act_sign_up_cl_nick_duplicate_check.setOnClickListener {
            var nick = act_sign_up_et_nick.text.toString()
            Log.e("nick중복버튼클릭",nick)
            if(nick!=""){
                signUpViewModel.userNickCheck(nick)
            }else{
                Toast.makeText(this,"닉네임을 입력해주세요!",Toast.LENGTH_SHORT)
            }}


        act_sign_up_cl_gender_female.isClickable = true
        act_sign_up_cl_gender_male.isClickable = true

        var maleCheck = false
        var femaleCheck = false
        var gender = 0

        act_sign_up_cl_gender_female.setOnClickListener {
            gender = 2
            femaleCheck = true
            act_sign_up_cl_gender_female.setBackgroundResource(R.drawable.item_sido_selected)
            act_sign_up_tv_gender_female.setTextColor(Color.parseColor("#fff4eb"))
            Toast.makeText(this, "여자클릭", Toast.LENGTH_SHORT).show()

            if (maleCheck) {
                maleCheck = false
                act_sign_up_cl_gender_male.setBackgroundResource(R.drawable.item_sido_unselected)
                act_sign_up_tv_gender_male.setTextColor(Color.parseColor("#333333"))
            } else {
//                act_sign_up_cl_gender_female.setBackgroundResource(R.drawable.item_sido_unselected)
//                act_sign_up_cl_gender_female.setTextColor(Color.parseColor("#333333"))
            }

        }

        act_sign_up_cl_gender_male.setOnClickListener {
            gender = 1
            maleCheck = true
            act_sign_up_cl_gender_male.setBackgroundResource(R.drawable.item_sido_selected)
            act_sign_up_tv_gender_male.setTextColor(Color.parseColor("#fff4eb"))
            Toast.makeText(this, "남자클릭", Toast.LENGTH_SHORT).show()

            if (femaleCheck) {
                femaleCheck = false
                act_sign_up_cl_gender_female.setBackgroundResource(R.drawable.item_sido_unselected)
                act_sign_up_tv_gender_female.setTextColor(Color.parseColor("#333333"))
            } else {
//                act_sign_up_cl_gender_female.setBackgroundResource(R.drawable.item_sido_unselected)
//                act_sign_up_cl_gender_female.setTextColor(Color.parseColor("#333333"))
            }

        }

        act_sign_up_bt_signup.setOnClickListener {
            var email = act_sign_up_et_email.text.toString()
            var nick = act_sign_up_et_nick.text.toString()
            var name = act_sign_up_et_name.text.toString()
            var pass = act_sign_up_et_pass.text.toString()
            var birth = act_sign_up_et_birth.text.toString()

            val signUpJsonObject = JSONObject()
            signUpJsonObject.put("userEmail", email)
            signUpJsonObject.put("userPw", pass)
            signUpJsonObject.put("userName", name)
            signUpJsonObject.put("userBirth", birth)
            signUpJsonObject.put("userGender", gender)
            signUpJsonObject.put("userNick", nick)
            val body = JsonParser.parseString(signUpJsonObject.toString()) as JsonObject
            signUpViewModel.signUp(body)
//
//            var dialog = AlertDialog.Builder(this)
//            dialog.setTitle(email)
//            dialog.setMessage(nick + name + pass)
//            dialog.show()

        }


//        val goMain = Intent(this,MainActivity::class.java)
//        startActivity(goMain)
    }




    fun setButtonActive(){
        act_sign_up_bt_signup.isClickable = true
        act_sign_up_bt_signup.setBackgroundColor(Color.parseColor("#f73859"))

    }


    fun observe() {
        var email = act_sign_up_et_email.toString()
        var nick = act_sign_up_et_nick.toString()
        var errormsg : String
        signUpViewModel.errorToast.observe(this, Observer {
            if (nick == null || nick.length <=2) {
                errormsg = "닉네임은 3글자 이상 입력해주세요!"
            } else if (email == null || email.length <= 5) {
                errormsg = "Email은 6글자 이상 입력해주세요!"
            }else {
                errormsg = "잠시 후 다시 시도해주세요"
            }
            Toast.makeText(this, errormsg, Toast.LENGTH_SHORT).show()
        })
        signUpViewModel.successMessage.observe(this, Observer {
            Log.e("signupActivity",it)
            if (it == "회원 가입 성공") {
                // 회원가입 성공!!!
                val goLogin = Intent(this, LoginActivity::class.java)
                startActivity(goLogin)
                finish()
            } else if (it.contains("Email")&&it.contains("가능")) {
                var dialog = AlertDialog.Builder(this)
                dialog.setTitle("아메일 중복 확인 결과")
                dialog.setMessage("사용할 수 있는 이메일입니다!")
                dialog.show()
                act_sign_up_et_email.isEnabled = false
                act_sign_up_cl_email_duplicate_check.isClickable = false
                act_sign_up_cl_email_duplicate_check.setBackgroundColor(Color.parseColor("#F73859"))
                act_sign_up_tv_email_duplicate_check.setTextColor(Color.parseColor("#FFFFFF"))
                act_sign_up_tv_email_duplicate_check.setText("확인 완료")
                duplicateEmailCheck = true

            } else if (it.contains("닉네임")&&it.contains("가능")) {
                var dialog = AlertDialog.Builder(this)
                dialog.setTitle("닉네임 중복 확인 결과")
                dialog.setMessage("사용할 수 있는 닉네임입니다!")
                dialog.show()
                act_sign_up_et_nick.isEnabled = false
                act_sign_up_cl_nick_duplicate_check.isClickable = false
                act_sign_up_cl_nick_duplicate_check.setBackgroundColor(Color.parseColor("#F73859"))
                act_sign_up_tv_nick_duplicate_check.setTextColor(Color.parseColor("#FFFFFF"))
                act_sign_up_tv_nick_duplicate_check.setText("확인 완료")
                duplicateNickCheck = true
            } else {
                Toast.makeText(this, "잘못된 접근입니다.",Toast.LENGTH_SHORT)
            }
        })
    }


}