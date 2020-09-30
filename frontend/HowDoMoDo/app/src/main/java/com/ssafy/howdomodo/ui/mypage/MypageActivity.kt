package com.ssafy.howdomodo.ui.mypage

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.`object`.UserCollection
import com.ssafy.howdomodo.data.datasource.model.Users
import com.ssafy.howdomodo.ui.bottomtap.BottomTabActivity
import com.ssafy.howdomodo.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_mypage.*
import kotlinx.android.synthetic.main.activity_signup.*
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MypageActivity : AppCompatActivity() {
    private val mypageViewModel: MyPageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)
        Update_Control().edit_init()

        observe()

        act_mypage_update_bt_nick_duplicate.setOnClickListener {
            var email = act_mypage_update_et_nick.text.toString()
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("중복체크")
            dialog.setMessage(email)
            dialog.show()
//            verifyEmail(email)
        }

        var info = intent.getParcelableExtra<Users>("info")
        var gender = info?.userGender
        var maleCheck=false
        var femaleCheck=false

        if (info != null) {
            act_mypage_update_et_nick.setText(info.userNick)
            act_mypage_update_et_name.setText(info.userName)
            act_mypage_update_et_birth.setText(info.userBirth)
            if(info.userGender==1){
                act_mypage_update_cl_gender_male.setBackgroundResource(R.drawable.item_sido_selected)
                act_mypage_update_tv_gender_male.setTextColor(Color.parseColor("#fff4eb"))
                maleCheck=true
            }else{
                act_mypage_update_cl_gender_female.setBackgroundResource(R.drawable.item_sido_selected)
                act_mypage_update_tv_gender_female.setTextColor(Color.parseColor("#fff4eb"))
                femaleCheck=true
            }
        }

        act_mypage_update_cl_gender_male.setOnClickListener{
            gender = 1
            maleCheck=true
            act_mypage_update_cl_gender_male.setBackgroundResource(R.drawable.item_sido_selected)
            act_mypage_update_tv_gender_male.setTextColor(Color.parseColor("#fff4eb"))
            Toast.makeText(this, "남자클릭",Toast.LENGTH_SHORT).show()

            if (femaleCheck) {
                femaleCheck=false
                act_mypage_update_cl_gender_female.setBackgroundResource(R.drawable.item_sido_unselected)
                act_mypage_update_tv_gender_female.setTextColor(Color.parseColor("#333333"))
            }
        }

        act_mypage_update_cl_gender_female.setOnClickListener{
            gender = 2
            femaleCheck=true
            act_mypage_update_cl_gender_female.setBackgroundResource(R.drawable.item_sido_selected)
            act_mypage_update_tv_gender_female.setTextColor(Color.parseColor("#fff4eb"))
            Toast.makeText(this, "여자클릭",Toast.LENGTH_SHORT).show()

            if (maleCheck) {
                maleCheck=false
                act_mypage_update_cl_gender_male.setBackgroundResource(R.drawable.item_sido_unselected)
                act_mypage_update_tv_gender_male.setTextColor(Color.parseColor("#333333"))
            }

        }

        act_mypage_update_bt_update.setOnClickListener {
            if(Update_Control().edit_check()) {
                var nick = act_mypage_update_et_nick.text.toString()
                var name = act_mypage_update_et_name.text.toString()
                var pass = act_mypage_update_et_pass.text.toString()
                var birth = act_mypage_update_et_birth.text.toString()
                val jsonObject = JSONObject()
                jsonObject.put("userCode",UserCollection.userCode)
                jsonObject.put("userPw", pass)
                jsonObject.put("userName", name)
                jsonObject.put("userBirth", birth)
                jsonObject.put("userEmail", UserCollection.userEmail)
                jsonObject.put("userNick", nick)
                val body = JsonParser.parseString(jsonObject.toString()) as JsonObject
                mypageViewModel.userUpdate(body)
            }

        }

    }
    inner class EditListener : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if(!s.isNullOrEmpty())
                act_mypage_update_bt_update.isEnabled = !act_mypage_update_et_nick.text.isNullOrEmpty() && !act_mypage_update_et_name.text.isNullOrEmpty() && !act_mypage_update_et_pass.text.isNullOrEmpty() && !act_mypage_update_et_pass_check.text.isNullOrEmpty() && !act_mypage_update_et_birth.text.isNullOrEmpty()
            else
                act_mypage_update_bt_update.isEnabled = false
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {       }
    }
    inner class Update_Control {
        fun edit_init() {
            act_mypage_update_et_nick.addTextChangedListener(EditListener())
            act_mypage_update_et_name.addTextChangedListener(EditListener())
            act_mypage_update_et_pass.addTextChangedListener(EditListener())
            act_mypage_update_et_pass_check.addTextChangedListener(EditListener())
            act_mypage_update_et_birth.addTextChangedListener(EditListener())
        }

        fun edit_check(): Boolean {
            if (act_mypage_update_et_nick.text.isNullOrEmpty() && act_mypage_update_et_name.text.isNullOrEmpty() && act_mypage_update_et_pass.text.isNullOrEmpty() && act_mypage_update_et_pass_check.text.isNullOrEmpty() && act_mypage_update_et_birth.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "모든 정보를 입력해주세요", Toast.LENGTH_LONG).show()
                return false
            }
            return true
        }
    }
    fun observe() {
        mypageViewModel.errorToast.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
        mypageViewModel.successMessage.observe(this, Observer {
            if (it == "회원 정보 수정 성공") {
                Log.e("kkkkk","Finish")
                val intent = Intent(this, BottomTabActivity::class.java)
                UserCollection.userName = act_mypage_update_et_name.text.toString()
                UserCollection.userNick = act_mypage_update_et_nick.text.toString()
                startActivity(intent)
                finish()
            }
        })
    }

}