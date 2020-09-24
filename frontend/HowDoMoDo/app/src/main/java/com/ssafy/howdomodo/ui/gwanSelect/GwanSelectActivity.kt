package com.ssafy.howdomodo.ui.gwanSelect

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.`object`.ObjectMovie
import com.ssafy.howdomodo.data.datasource.model.Gwan
import com.ssafy.howdomodo.data.datasource.model.MovieTime
import kotlinx.android.synthetic.main.activity_gwan_select.*

class GwanSelectActivity : AppCompatActivity() {
    private val data = listOf<List<Gwan>>(
        listOf(
            Gwan(
                "2D", 1, 145,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:25", 24, false),
                    MovieTime("14:25", "16:45", 25, false),
                    MovieTime("16:55", "18:35", 63, false),
                )
            ),
            Gwan(
                "2D", 4, 232,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:25", 23, false),
                    MovieTime("14:25", "16:45", 54, false),
                    MovieTime("16:55", "18:35", 87, false),
                    MovieTime("16:55", "18:35", 25, false),
                    MovieTime("19:00", "20:35", 64, false),
                    MovieTime("20:50", "22:35", 9, false),
                    MovieTime("22:55", "00:35", 8, false),
                )
            ),
            Gwan(
                "2D", 2, 142,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:20", 23, false),
                    MovieTime("13:20", "14:45", 32, false),
                    MovieTime("15:55", "16:00", 78, false),
                )
            ),
            Gwan(
                "2D", 4, 232,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:25", 23, false),
                    MovieTime("14:25", "16:45", 54, false),
                    MovieTime("16:55", "18:35", 87, false),
                )
            ),
            Gwan(
                "3D", 8, 51,
                arrayListOf<MovieTime>(
                    MovieTime("08:25", "10:45", 13, false),
                    MovieTime("19:15", "121:00", 53, false)
                )
            )
        ),
        listOf(
            Gwan(
                "2D", 5, 67,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:25", 23, false),
                    MovieTime("12:25", "12:25", 55, false),
                    MovieTime("14:25", "12:25", 63, false),
                    MovieTime("16:25", "12:25", 73, false),
                )
            ),
            Gwan(
                "2D", 4, 64,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:25", 83, false),
                    MovieTime("10:25", "12:25", 84, false)
                )
            )
        ),
        listOf(
            Gwan(
                "2D", 8, 44,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:25", 42, false),
                    MovieTime("14:25", "12:25", 56, false),
                    MovieTime("16:25", "12:25", 25, false),
                )
            ),
            Gwan(
                "3D", 18, 44,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:25", 42, false),
                    MovieTime("12:25", "14:25", 56, false),
                    MovieTime("16:25", "18:25", 25, false),
                )
            ),
            Gwan(
                "2D", 11, 234,
                arrayListOf<MovieTime>(
                    MovieTime("12:25", "14:25", 23, false),
                    MovieTime("15:25", "18:22", 23, false)
                )
            )
        ),
        listOf(
            Gwan(
                "2D", 5, 67,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:25", 23, false),
                    MovieTime("12:25", "12:25", 55, false),
                    MovieTime("14:25", "12:25", 63, false),
                    MovieTime("16:25", "12:25", 73, false),
                )
            ),
            Gwan(
                "2D", 4, 64,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:25", 83, false),
                    MovieTime("10:25", "12:25", 84, false)
                )
            )
        ),
        listOf(
            Gwan(
                "2D", 9, 31,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:35", 23, false),
                    MovieTime("20:25", "22:25", 23, false),
                )
            ),
            Gwan(
                "3D", 3, 123,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:25", 23, false),
                )
            )
        ),
        listOf(
            Gwan(
                "2D", 5, 67,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:25", 23, false),
                    MovieTime("12:25", "12:25", 55, false),
                    MovieTime("14:25", "12:25", 63, false),
                    MovieTime("16:25", "12:25", 73, false),
                )
            ),
            Gwan(
                "2D", 4, 64,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:25", 83, false),
                    MovieTime("10:25", "12:25", 84, false)
                )
            )
        ), listOf(
            Gwan(
                "2D", 8, 44,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:25", 42, false),
                    MovieTime("14:25", "12:25", 56, false),
                    MovieTime("16:25", "12:25", 25, false),
                )
            ),
            Gwan(
                "3D", 18, 44,
                arrayListOf<MovieTime>(
                    MovieTime("10:25", "12:25", 42, false),
                    MovieTime("12:25", "14:25", 56, false),
                    MovieTime("16:25", "18:25", 25, false),
                )
            ),
            Gwan(
                "2D", 11, 234,
                arrayListOf<MovieTime>(
                    MovieTime("12:25", "14:25", 23, false),
                    MovieTime("15:25", "18:22", 23, false)
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

        act_gwan_tv_movie_title.text = ObjectMovie.movieTitle
        act_gwan_tv_theater_name.text = ObjectMovie.movieTheater


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