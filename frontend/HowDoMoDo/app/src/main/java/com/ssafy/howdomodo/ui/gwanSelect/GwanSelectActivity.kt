package com.ssafy.howdomodo.ui.gwanSelect

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Gwan
import com.ssafy.howdomodo.data.datasource.model.MovieTime
import kotlinx.android.synthetic.main.activity_gwan_select.*

class GwanSelectActivity : AppCompatActivity() {
    private val data = listOf<Gwan>(Gwan("2관", 3, 123,
            arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:25", 23, false),
                    MovieTime("10:25", "12:25", 23, false),
                    MovieTime("10:25", "12:25", 23, false),
                    MovieTime("10:25", "12:25", 23, false),
                    MovieTime("10:25", "12:25", 23, false),
                    MovieTime("10:25", "12:25", 23, false),
                    MovieTime("10:25", "12:25", 23, false),
                    MovieTime("10:25", "12:25", 23, false))),
            Gwan("2관", 3, 123,
                    arrayListOf<MovieTime>(MovieTime("10:25", "12:25", 23, false),
                            MovieTime("10:25", "12:25", 23, false))))
    lateinit var dayAdapter: DayAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gwan_select)


        dayAdapter = DayAdapter(object : DayViewHolder.DayClickListener {
            override fun onclick(position: Int, textView: TextView) {
                if (!dayAdapter.getClicked(position)) {
                    dayAdapter.setClicked(dayAdapter.getClickedDay(), false)
                    dayAdapter.setClicked(position, true)
                }
            }
        })
        dayAdapter.setDayData(Week.times)
        act_gwan_rv_day.adapter = dayAdapter
        act_gwan_rv_day.layoutManager = LinearLayoutManager(this).also { it.orientation = LinearLayoutManager.HORIZONTAL }
        act_gwan_rv_day.setHasFixedSize(true)

        val gwanAdapter = GwanAdapter()
        gwanAdapter.setGwanData(data)
        act_gwan_rv_gwan.adapter = gwanAdapter
        gwanAdapter.setGwanListener(object : GwanViewHolder.ClickListener{
            override fun onClick() {
                gwanAdapter.notifyDataSetChanged()

            }
        })
        act_gwan_rv_gwan.layoutManager = LinearLayoutManager(this)
        act_gwan_rv_gwan.setHasFixedSize(true)
    }
}