package com.ssafy.howdomodo.ui.theater

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.`object`.TheaterCollection
import com.ssafy.howdomodo.ui.main.WebviewActivity
import kotlinx.android.synthetic.main.activity_ticket_info.*
import kotlinx.android.synthetic.main.item_main_movie.view.*

class TicketInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_info)

//        TheaterCollection.mvDate = "2020-10-15"
//        TheaterCollection.mvType = "2D (자막)"
//        TheaterCollection.mvTheater = "CGV 강남대로점"
//        TheaterCollection.mvTheaterNum = "1관 (232석)"
//        TheaterCollection.mvTitle = "테넷"
//        TheaterCollection.mvPoster = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDA4MTVfMjEw%2FMDAxNTk3NDc0MzA2NTYz.uN2BhMpntHbZ1YSOxS-HHf6a6Li0i6zvCGmszCdCFlwg.nTcvg9U86OkJh7QszSRfOAYB0ACZMDh4aTgW4tB0nfQg.JPEG.tilney%2Ftenet_ver11_xxlg.jpg&type=sc960_832"
//        TheaterCollection.mvTheaterName = "cgv"

        Glide.with(this).load(TheaterCollection.mvPoster).into(act_ticket_info_iv_poster)
        act_ticket_info_tv_mvdate.setText(TheaterCollection.mvDate)
        act_ticket_info_tv_mvtype.setText(TheaterCollection.mvType)
        act_ticket_info_tv_mvtheater.setText(TheaterCollection.mvTheater)
        act_ticket_info_tv_mvtheater_num.setText(TheaterCollection.mvTheaterNum)
        act_ticket_info_tv_mvtitle.setText(TheaterCollection.mvTitle)

        act_ticket_info_cl_btn_next.setOnClickListener{

            if(TheaterCollection.mvTheaterName == "cgv"){
                var url = "http://www.cgv.co.kr/"
                val intent = Intent(this, WebviewActivity::class.java)
                intent.putExtra("url", url)
                startActivity(intent)
            }
        }





    }
}