package com.ssafy.howdomodo.ui.signup

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.item_sido.view.*
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        observe()

        val str = intent.getStringExtra("btnName")
        Toast.makeText(this, str.toString(), Toast.LENGTH_SHORT).show()



        act_sign_up_bt_email_duplicate_check.setOnClickListener {
            var email = act_sign_up_et_email.text.toString()
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("ㅎㅇ")
            dialog.setMessage(email)
            dialog.show()
//            verifyEmail(email)
        }

        act_sign_up_cl_gender_female.isClickable=true
        act_sign_up_cl_gender_male.isClickable=true

        var maleCheck=false
        var femaleCheck=false
        var gender = 0

        act_sign_up_cl_gender_female.setOnClickListener{
            gender = 2
            femaleCheck=true
            act_sign_up_cl_gender_female.setBackgroundResource(R.drawable.item_sido_selected)
            act_sign_up_tv_gender_female.setTextColor(Color.parseColor("#fff4eb"))
            Toast.makeText(this, "여자클릭",Toast.LENGTH_SHORT).show()

            if (maleCheck) {
                maleCheck=false
                act_sign_up_cl_gender_male.setBackgroundResource(R.drawable.item_sido_unselected)
                act_sign_up_tv_gender_male.setTextColor(Color.parseColor("#333333"))
            } else {
//                act_sign_up_cl_gender_female.setBackgroundResource(R.drawable.item_sido_unselected)
//                act_sign_up_cl_gender_female.setTextColor(Color.parseColor("#333333"))
            }

        }

        act_sign_up_cl_gender_male.setOnClickListener{
            gender = 1
            maleCheck=true
            act_sign_up_cl_gender_male.setBackgroundResource(R.drawable.item_sido_selected)
            act_sign_up_tv_gender_male.setTextColor(Color.parseColor("#fff4eb"))
            Toast.makeText(this, "남자클릭",Toast.LENGTH_SHORT).show()

            if (femaleCheck) {
                femaleCheck=false
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
    fun observe() {
        signUpViewModel.errorToast.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
        signUpViewModel.successMessage.observe(this, Observer {
            if (it == "회원 가입 성공") {
                // 회원가입 성공!!!
                val goLogin = Intent(this, LoginActivity::class.java)
                startActivity(goLogin)
                finish()
            }
        })
    }

//    private fun verifyEmail(email: String) {
//        // 보내는 메일 주소와 비밀번호
//        var sender = "daeyongssafy@gmail.com";
//        var sender_password = "rlaeodyd1";
//
//        var title = "회원가입을 위한 이메일 인증코드입니다."
//        var code = Random().nextInt(999999)
//        var body = "인증번호는 " + code + "입니다. "
//
//        Toast.makeText(this, email.toString(), Toast.LENGTH_SHORT).show()
//
//        val props = Properties();
//        props.put("mail.smtp.auth", "true")
//        props.put("mail.smtp.starttls.enable", "true")
//        props.put("mail.smtp.host", "smtp.gmail.com")
//        props.put("mail.smtp.port", "587")
//
//        // 비밀번호 인증으로 세션 생성
//        val session = Session.getInstance(props,
//                object : javax.mail.Authenticator() {
//                    override fun getPasswordAuthentication(): PasswordAuthentication {
//                        return PasswordAuthentication(sender, sender_password)
//                    }
//                })
//
//        // 메시지 객체 만들기
//        val message = MimeMessage(session)
//        message.setFrom(InternetAddress(sender))
//
//        // 수신자 설정
//        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email.toString()))
//        message.subject = title
//        message.setText(body)
//
//        // 전송
//        Transport.send(message)
//    }
}