package com.ssafy.howdomodo.ui.signup

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.howdomodo.R
import kotlinx.android.synthetic.main.activity_signup.*

//import javax.mail.Message
//import javax.mail.PasswordAuthentication
//import javax.mail.Session
//import javax.mail.Transport
//import javax.mail.internet.InternetAddress
//import javax.mail.internet.MimeMessage

class SignupActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val str = intent.getStringExtra("btnName")
        Toast.makeText(this, str.toString(), Toast.LENGTH_SHORT).show()



        act_sign_up_bt_email_auth.setOnClickListener {
            var email = act_sign_up_et_email.text.toString()
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("ㅎㅇ")
            dialog.setMessage(email)
            dialog.show()
//            verifyEmail(email)
        }

        act_sign_up_bt_signup.setOnClickListener {
            var email = act_sign_up_et_email.text.toString()
            var nick = act_sign_up_et_nick.text.toString()
            var name = act_sign_up_et_name.text.toString()
            var pass = act_sign_up_et_pass.text.toString()
            var birth = act_sign_up_et_birth.text.toString()
            var sex = "";

            var dialog = AlertDialog.Builder(this)
            dialog.setTitle(email)
            dialog.setMessage(nick + name + pass)
            dialog.show()

        }


//        val goMain = Intent(this,MainActivity::class.java)
//        startActivity(goMain)
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