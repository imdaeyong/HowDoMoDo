package com.ssafy.howdomodo.ui.gwanSelect

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.`object`.ObjectMovie
import com.ssafy.howdomodo.`object`.TheaterCollection
import kotlinx.android.synthetic.main.activity_gwan_select.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat

class GwanSelectActivity : AppCompatActivity() {

    private val viewModel: GwanSelectViewModel by viewModel()
    lateinit var dayAdapter: DayAdapter
    lateinit var gwanAdapter: GwanAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gwan_select)


        gwanAdapter = GwanAdapter()
        viewModel.getGwanData("CGV", "강남", "20201005", "담보")
        act_gwan_rv_gwan.layoutManager = LinearLayoutManager(this)
        act_gwan_rv_gwan.setHasFixedSize(true)

        act_gwan_tv_movie_title.text = ObjectMovie.movieTitle
        act_gwan_tv_theater_name.text = ObjectMovie.movieTheater
        setObserve()

        dayAdapter = DayAdapter(object : DayViewHolder.DayClickListener {
            override fun onclick(position: Int, textView: TextView) {
                if (!dayAdapter.getClicked(position)) {
                    val clicked = gwanAdapter.getClickedMovieTime()
                    if (clicked != null) {
                        gwanAdapter.setClicked(clicked[0], clicked[1], false)
                    }
                    btnToggle()
                    val timeFormat = SimpleDateFormat("yyyyMMdd")
                    val timeStr = SimpleDateFormat("yyyy-MM-dd hh:mm")
                    Log.e("tt", timeFormat.format(Week.times[position].dayDate).toString())
                    TheaterCollection.mvDate =
                        timeStr.format(Week.times[position].dayDate).toString()


                    dayAdapter.setClicked(dayAdapter.getClickedDay(), false)
                    dayAdapter.setClicked(position, true)
                    viewModel.getGwanData(
                        "CGV",
                        "강남",
                        timeFormat.format(Week.times[position].dayDate).toString(),
                        "담보"
                    )
                }
            }
        })
        dayAdapter.setDayData(Week.times)
        act_gwan_rv_day.adapter = dayAdapter
        act_gwan_rv_day.layoutManager = LinearLayoutManager(this).also { it.orientation = LinearLayoutManager.HORIZONTAL }
        act_gwan_rv_day.setHasFixedSize(true)


        act_gwan_rv_gwan.adapter = gwanAdapter
        gwanAdapter.setGwanListener(object : GwanViewHolder.ClickListener {
            override fun onClick() {
                btnToggle()
                gwanAdapter.notifyDataSetChanged()

            }
        })

        act_gwan_cl_btn_next.setOnClickListener {
            Log.e("cl!!", "asd")
        }

        act_gwan_cl_btn_next.isClickable = false

    }

    private fun setObserve() {
        viewModel.gwanData.observe(this, Observer {
            gwanAdapter.setGwanData(it)
            act_gwan_cl_no_gwan.visibility = View.GONE

        })
        viewModel.gwanNull.observe(this, Observer {
//            Toast.makeText(this, "빈값입니다", Toast.LENGTH_SHORT).show()
            act_gwan_cl_no_gwan.visibility = View.VISIBLE
        })
    }

    fun btnToggle() {
        val clicked = gwanAdapter.getClickedMovieTime()
        if (clicked == null) {
            act_gwan_cl_btn_next.setBackgroundColor(Color.parseColor("#aaaaaa"))
            act_gwan_cl_btn_next.isClickable = false
        } else {
            act_gwan_cl_btn_next.setBackgroundColor(Color.parseColor("#f73859"))
            act_gwan_cl_btn_next.isClickable = true
        }
    }
}