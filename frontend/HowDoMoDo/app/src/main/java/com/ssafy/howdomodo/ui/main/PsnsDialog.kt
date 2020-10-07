package com.ssafy.howdomodo.ui.main

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.ssafy.howdomodo.R

private lateinit var listener : PsnsDialog.PsnsDialogOKClickedListener
class PsnsDialog(context : Context) {
    private val dlg = Dialog(context)   //부모 액티비티의 context 가 들어감
    private lateinit var lblDesc : TextView
    private lateinit var btnOK : Button
    private lateinit var btnCancel : Button
    private lateinit var listener : PsnsDialogOKClickedListener

    fun start(content : String) {
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(R.layout.item_psns_dialog)     //다이얼로그에 사용할 xml 파일을 불러옴
        dlg.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함

        lblDesc = dlg.findViewById(R.id.item_psns_content)
        lblDesc.text = content

        btnOK = dlg.findViewById(R.id.item_psns_btn_ok)
        btnOK.setOnClickListener {

            //TODO: 부모 액티비티로 내용을 돌려주기 위해 작성할 코드
            listener.onOKClicked("확인")
            dlg.dismiss()
        }

        btnCancel = dlg.findViewById(R.id.item_psns_btn_cancel)
        btnCancel.setOnClickListener {
            listener.onOKClicked("취소")
            dlg.dismiss()
        }

        dlg.show()
    }
    fun setOnOKClickedListener(listener: (String) -> Unit) {
        this.listener = object: PsnsDialogOKClickedListener {
            override fun onOKClicked(content: String) {
                listener(content)
            }
        }
    }

    interface PsnsDialogOKClickedListener {
        fun onOKClicked(content : String)
    }
}
