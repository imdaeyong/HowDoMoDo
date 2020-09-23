package com.ssafy.howdomodo.ui.gwanSelect

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Gwan
import com.ssafy.howdomodo.data.datasource.model.MovieTime
import kotlinx.android.synthetic.main.activity_gwan_select.*

class GwanSelectActivity : AppCompatActivity() {
    private val data = listOf<List<Gwan>>(
        listOf(
            Gwan(
                "2관", 3, 123,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:25", 23, false),
                    MovieTime("12:25", "12:25", 23, false),
                    MovieTime("14:25", "12:25", 23, false),
                    MovieTime("16:25", "12:25", 23, false),
                )
            ),
            Gwan(
                "4관", 3, 123,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:25", 23, false),
                    MovieTime("10:25", "12:25", 23, false)
                )
            )
        ),
        listOf(
            Gwan(
                "5관", 3, 123,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:25", 23, false),
                    MovieTime("16:25", "18:25", 23, false),
                    MovieTime("20:25", "22:25", 23, false),
                )
            ),
            Gwan(
                "9관", 3, 123,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:25", 23, false),
                )
            )
        ),
    )
    lateinit var dayAdapter: DayAdapter
    lateinit var gwanAdapter: GwanAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gwan_select)

        gwanAdapter = GwanAdapter()
        gwanAdapter.setGwanData(data[0])
        act_gwan_rv_gwan.layoutManager = LinearLayoutManager(this)
        act_gwan_rv_gwan.setHasFixedSize(true)


        dayAdapter = DayAdapter(object : DayViewHolder.DayClickListener {
            override fun onclick(position: Int, textView: TextView) {
                if (!dayAdapter.getClicked(position)) {
                    val clicked = gwanAdapter.getClickedMovieTime()
                    if (clicked != null) {
                        gwanAdapter.setClicked(clicked[0], clicked[1], false)
                    }
                    btnToggle()

                    dayAdapter.setClicked(dayAdapter.getClickedDay(), false)
                    dayAdapter.setClicked(position, true)
                    gwanAdapter.setGwanData(data[position])
                }
            }
        })
        dayAdapter.setDayData(Week.times)
        act_gwan_rv_day.adapter = dayAdapter
        act_gwan_rv_day.layoutManager = LinearLayoutManager(this).also { it.orientation = LinearLayoutManager.HORIZONTAL }
        act_gwan_rv_day.setHasFixedSize(true)


        act_gwan_rv_gwan.adapter = gwanAdapter
        gwanAdapter.setGwanListener(object : GwanViewHolder.ClickListener{
            override fun onClick() {
                btnToggle()
                gwanAdapter.notifyDataSetChanged()

            }
        })



    }
    fun btnToggle(){
        val clicked = gwanAdapter.getClickedMovieTime()
        if (clicked == null) {
            act_gwan_cl_btn_next.setBackgroundColor(Color.parseColor("#aaaaaa"))
        }else{
            act_gwan_cl_btn_next.setBackgroundColor(Color.parseColor("#f73859"))
        }
    }
}