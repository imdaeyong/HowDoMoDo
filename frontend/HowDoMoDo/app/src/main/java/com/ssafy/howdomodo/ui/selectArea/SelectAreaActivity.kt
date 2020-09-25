package com.ssafy.howdomodo.ui.selectArea

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.`object`.ObjectMovie
import com.ssafy.howdomodo.data.datasource.model.Gugun
import com.ssafy.howdomodo.data.datasource.model.Sido
import com.ssafy.howdomodo.data.datasource.model.Theater
import com.ssafy.howdomodo.ui.gwanSelect.GwanSelectActivity
import kotlinx.android.synthetic.main.activity_select_area.*

class SelectAreaActivity : AppCompatActivity() {
    companion object {
        val boolList = arrayListOf<Boolean>(false, false, false)
    }

    var theaterList = arrayListOf<Theater>(
        Theater("CGV", "강남1점", "5", false, 37.4874592, 127.0471432, false),
        Theater("CGV", "강남2점", "6", false, 37.516363, 127.0219782, false),
        Theater("메가박스", "강남1점", "5", false, 37.5004008, 127.0270069, false),
        Theater("메가박스", "강남2점", "4", false, 37.5128784, 127.0572911, false),
        Theater("롯데시네마", "강남1점", "1", false, 37.5016424, 127.0263372, false),
        Theater("롯데시네마", "강남2점", "2", false, 37.5243393, 127.0294194, false),
        Theater("롯데시네마", "강남3점", "3", false, 37.5228972, 127.0370162, false),
        Theater("CGV", "강남1점", "5", false, 37.5243393, 127.0294194, false),


        )

    var sidoList = arrayListOf<Sido>(
        Sido("서울특별시", false),
        Sido("경기도", false),
        Sido("충청남도", false),
        Sido("충청북도", false),
        Sido("부산", false),
        Sido("강원도", false),
        Sido("강원도", false),
        Sido("강원도", false),
        Sido("강원도", false),
        Sido("강원도", false),
        Sido("강원도", false),
        Sido("강원도", false),
        Sido("강원도", false),

        )

    var gugunList = arrayListOf<Gugun>(
        Gugun("강남", false),
        Gugun("강남대로", false),
        Gugun("강동", false),
        Gugun("군자", false),
        Gugun("천호", false),
        Gugun("신림", false),
        Gugun("건국대입구", false),
        Gugun("서울대입구", false),
        Gugun("사당", false),
    )

    lateinit var sidoAdapter: SidoAdapter
    lateinit var gugunAdapter: GugunAdapter
    lateinit var theaterAdapter :TheaterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_area)

        boolList[0]=false
        boolList[1]=false
        boolList[2]=false

        sidoAdapter = SidoAdapter(
            object : SidoAdapter.SidoViewHolder.SidoClickListener {
                override fun onclick(position: Int, textView: TextView) {
                    if (!sidoAdapter.getClicked(position)) {
                        if(sidoAdapter.getClickedSido()!=-1) {
                            sidoAdapter.setClicked(sidoAdapter.getClickedSido(), false)
                        }
                        boolList[0] = true
                        sidoAdapter.setClicked(position, true)
                    } else if(sidoAdapter.getClicked(position)) {
                        sidoAdapter.setClicked(sidoAdapter.getClickedSido(), false)
                        boolList[0] = false
                    }
                    setButtonActive()
                }

            })
        sidoAdapter.setSidoData(sidoList)
        act_select_rv_sidos.adapter = sidoAdapter
        var sidolm = LinearLayoutManager(this)
        act_select_rv_sidos.layoutManager = sidolm
        act_select_rv_sidos.setHasFixedSize(true)


        gugunAdapter = GugunAdapter(
            object : GugunViewHolder.GugunClickListener {
                override fun onclick(position: Int, textView: TextView) {
                    if (!gugunAdapter.getClicked(position)) {
                        if(gugunAdapter.getClickedGugun()!=-1) {
                            gugunAdapter.setClicked(gugunAdapter.getClickedGugun(), false)
                        }
                        boolList[1] = true
                        gugunAdapter.setClicked(position, true)
                    } else if(gugunAdapter.getClicked(position)) {
                        gugunAdapter.setClicked(gugunAdapter.getClickedGugun(), false)
                        boolList[1] = false
                    }
                    setButtonActive()
                }

            })
        gugunAdapter.setGugunData(gugunList)
        act_select_rv_guguns.adapter = gugunAdapter
        var gugunlm = LinearLayoutManager(this)
        act_select_rv_guguns.layoutManager = gugunlm
        act_select_rv_guguns.setHasFixedSize(true)


        theaterAdapter = TheaterAdapter(
            object : TheaterAdapter.TheaterViewHolder.TheaterClickListener {
                override fun onclick(position: Int, textView: TextView) {
                    if (!theaterAdapter.getClicked(position)) {
                        if(theaterAdapter.getClickedTheater()!=-1) {
                            theaterAdapter.setClicked(theaterAdapter.getClickedTheater(), false)
                        }
                        boolList[2] = true
                        theaterAdapter.setClicked(position, true)
                        ObjectMovie.movieTheater =
                            theaterAdapter.theaterData[position].kind + " " + theaterAdapter.theaterData[position].name
                    } else if(theaterAdapter.getClicked(position)) {
                        theaterAdapter.setClicked(theaterAdapter.getClickedTheater(), false)
                        boolList[2] = false
                    }
                    setButtonActive()
                }

            })
        theaterAdapter.setTheaterData(theaterList)
        act_select_area_rv_theaters.adapter = theaterAdapter
        var theaterlm = LinearLayoutManager(this)
        act_select_area_rv_theaters.layoutManager = theaterlm
        act_select_area_rv_theaters.setHasFixedSize(true)


//
//        var mapView = MapView(this)
//        var mapViewController = act_select_area_rl_map_view as ViewGroup
//        mapViewController.addView(mapView)
//
//        act_select_area_sw_map.setOnCheckedChangeListener{ buttonView, isChecked ->
//            if(isChecked) {
//                act_select_area_rl_map_view.visibility= View.VISIBLE
//                act_select_area_rv_theaters.visibility = View.GONE
//            }else {
//                act_select_area_rl_map_view.visibility= View.GONE
//                act_select_area_rv_theaters.visibility = View.VISIBLE
//            }
//
//
//        }

        act_select_area_cl_btn_next.setOnClickListener {
            val intent = Intent(this, GwanSelectActivity::class.java)
            startActivity(intent)
        }
    }

    fun setButtonActive() {
        var token = true
        Log.e("bbb", boolList.toString())
        for (i in boolList) {
            if (!i) {
                token = false
                break
            }

        }
        if (token) {
            act_select_area_cl_btn_next.setBackgroundColor(Color.parseColor("#f73859"))
            act_select_area_cl_btn_next.isClickable = true
        } else {
            act_select_area_cl_btn_next.setBackgroundColor(Color.parseColor("#aaaaaa"))
            act_select_area_cl_btn_next.isClickable = false
        }
    }
}
