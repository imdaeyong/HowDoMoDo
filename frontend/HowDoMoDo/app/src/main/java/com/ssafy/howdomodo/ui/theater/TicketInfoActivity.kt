package com.ssafy.howdomodo.ui.theater

import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.`object`.TheaterCollection
import com.ssafy.howdomodo.ui.BasicActivity
import com.ssafy.howdomodo.ui.last.LastActivity
import kotlinx.android.synthetic.main.activity_ticket_info.*


class TicketInfoActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_info)
        actList.add(this)

        val cgv = "com.cgv.android.movieapp"
        val lotte = "kr.co.lottecinema.lcm"
        val mega = "com.megabox.mop"
//        TheaterCollection.mvDate = "2020-10-15"
//        TheaterCollection.mvType = "2D (자막)"
//        TheaterCollection.mvTheater = "CGV 강남대로점"
//        TheaterCollection.mvTheaterNum = "1관 (232석)"
//        TheaterCollection.mvTitle = "테넷"
//        TheaterCollection.mvPoster = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDA4MTVfMjEw%2FMDAxNTk3NDc0MzA2NTYz.uN2BhMpntHbZ1YSOxS-HHf6a6Li0i6zvCGmszCdCFlwg.nTcvg9U86OkJh7QszSRfOAYB0ACZMDh4aTgW4tB0nfQg.JPEG.tilney%2Ftenet_ver11_xxlg.jpg&type=sc960_832"

        Glide.with(this).load(TheaterCollection.mvPoster).into(act_ticket_info_iv_poster)
        act_ticket_info_tv_mvdate.setText(TheaterCollection.mvDate)
        act_ticket_info_tv_mvtype.setText(TheaterCollection.mvType)
        act_ticket_info_tv_mvtheater.setText(TheaterCollection.mvTheater)
        act_ticket_info_tv_mvtheater_num.setText(TheaterCollection.mvTheaterNum)
        act_ticket_info_tv_mvtitle.setText(TheaterCollection.mvTitle)

        act_ticket_info_cl_btn_next.setOnClickListener{
            //Log.e("TEST",TheaterCollection.mvTheater)

            var sintent= Intent(this, LastActivity::class.java)
            startActivity(sintent)


            if(TheaterCollection.mvTheater.contains("CGV")){
                if(getPackageList(cgv)){
                    val intent = this.getPackageManager().getLaunchIntentForPackage(cgv);
                    intent!!.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else{
                    val url = "https://play.google.com/store/apps/details?id=" + cgv +"&hl=ko"
                    val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(i)
                }
            }else if(TheaterCollection.mvTheater.contains("롯데시네마")){
                if(getPackageList(lotte)){
                    val intent = this.getPackageManager().getLaunchIntentForPackage(lotte);
                    intent!!.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else{
                    val url = "https://play.google.com/store/apps/details?id=" + lotte +"&hl=ko"
                    val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(i)
                }
            } else if(TheaterCollection.mvTheater.contains("메가박스")){
                if(getPackageList(mega)){
                    val intent = this.getPackageManager().getLaunchIntentForPackage(mega);
                    intent!!.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent)
                }else{
                    val url = "https://play.google.com/store/apps/details?id=" + mega +"&hl=ko"
                    val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(i)
                }
            }

//            actFinish()

//            val handler = Handler()
//            handler.postDelayed({
//                var intent= Intent(this, LastActivity::class.java)
//                startActivity(intent)
//            },1000)
        }
    }
    fun getPackageList(url: String): Boolean {
        var isExist = false
        val pkgMgr = packageManager
        val mApps: List<ResolveInfo>
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        mApps = pkgMgr.queryIntentActivities(mainIntent, 0)
        try {
            for (i in mApps.indices) {
                if (mApps[i].activityInfo.packageName.startsWith(url)) {
                    isExist = true
                    break
                }
            }
        } catch (e: Exception) {
            isExist = false
        }
        return isExist
    }
}