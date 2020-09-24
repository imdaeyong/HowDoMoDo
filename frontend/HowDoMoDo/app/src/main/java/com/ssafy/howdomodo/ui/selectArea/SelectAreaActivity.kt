package com.ssafy.howdomodo.ui.selectArea

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Gugun
import com.ssafy.howdomodo.data.datasource.model.Sido
import com.ssafy.howdomodo.data.datasource.model.Theater
import kotlinx.android.synthetic.main.activity_select_area.*
import kotlinx.android.synthetic.main.item_map.*
import net.daum.mf.map.api.MapView

class SelectAreaActivity : AppCompatActivity() {
    var theaterList = arrayListOf<Theater>(
        Theater("CGV", "강남1점", "5",  false,37.4874592	,127.0471432,false),
        Theater("CGV", "강남2점", "6",  false    ,37.516363	,127.0219782,false),
        Theater("메가박스", "강남1점", "5",  false    ,37.5004008	,127.0270069,false),
        Theater("메가박스", "강남2점", "4",  false    ,37.5128784	,127.0572911,false),
        Theater("롯데시네마", "강남1점", "1",  false    ,37.5016424	,127.0263372,false),
        Theater("롯데시네마", "강남2점", "2",  false    ,37.5243393	,127.0294194,false),
        Theater("롯데시네마", "강남3점", "3",  false    ,37.5228972	,127.0370162,false),
        Theater("CGV", "강남1점", "5", false    ,37.5243393	,127.0294194,false),

        )

    var sidoList = arrayListOf<Sido>(
        Sido("서울", false),
        Sido("경기도", false),
        Sido("충청남도", false),
        Sido("충청북도", false),
        Sido("부산", false),
        Sido("전라남도", false),
        Sido("전라북도", false),
        Sido("제주", false),
        Sido("인천", false),
        Sido("울산", false),
        Sido("세종", false),
        Sido("대전", false),
        Sido("대구", false),
        Sido("강원도", false),
        Sido("경상남도", false),
        Sido("경상북도", false),
        Sido("광주", false),
    )

    var gugunList = arrayListOf<Gugun>(
        Gugun("강남구", false),
        Gugun("강동구", false),
        Gugun("강북구", false),
        Gugun("강서구", false),
        Gugun("관악구", false),
        Gugun("광진구", false),
        Gugun("구로구", false),
        Gugun("금천구", false),
        Gugun("노원구", false),
        Gugun("도봉구", false),
        Gugun("동대문구", false),
        Gugun("동작구", false),
        Gugun("마포구", false),
        Gugun("서대문구", false),
        Gugun("서초구", false),
        Gugun("성동구", false),
        Gugun("성북구", false),
        Gugun("송파구", false),
        Gugun("양천구", false),
        Gugun("영등포구", false),
        Gugun("용산구", false),
        Gugun("은평구", false),
        Gugun("종로구", false),
        Gugun("중구", false),
        Gugun("중랑구", false),

        )

    lateinit var sidoAdapter: SidoAdapter
    lateinit var gugunAdapter: GugunAdapter
    lateinit var theaterAdapter :TheaterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_area)

        sidoAdapter = SidoAdapter(
            object : SidoAdapter.SidoViewHolder.SidoClickListener {
                override fun onclick(position: Int, textView: TextView) {
                    if (!sidoAdapter.getClicked(position)) {
                        if (sidoAdapter.getClickedSido() != -1) {
                            sidoAdapter.setClicked(sidoAdapter.getClickedSido(), false)
                        }
                        sidoAdapter.setClicked(position, true)
                    } else if (sidoAdapter.getClicked(position)) {
                        sidoAdapter.setClicked(sidoAdapter.getClickedSido(), false)
                    }
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
                        if (gugunAdapter.getClickedGugun() != -1) {
                            gugunAdapter.setClicked(gugunAdapter.getClickedGugun(), false)
                        }
                        gugunAdapter.setClicked(position, true)
                    } else if (gugunAdapter.getClicked(position)) {
                        gugunAdapter.setClicked(gugunAdapter.getClickedGugun(), false)
                    }

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
                        if (theaterAdapter.getClickedTheater() != -1) {
                            theaterAdapter.setClicked(theaterAdapter.getClickedTheater(), false)
                        }
                        theaterAdapter.setClicked(position, true)
                    } else if (theaterAdapter.getClicked(position)) {
                        theaterAdapter.setClicked(theaterAdapter.getClickedTheater(), false)
                    }

                }

            })
        theaterAdapter.setTheaterData(theaterList)
        act_select_area_rv_theaters.adapter = theaterAdapter
        var theaterlm = LinearLayoutManager(this)
        act_select_area_rv_theaters.layoutManager = theaterlm
        act_select_area_rv_theaters.setHasFixedSize(true)


        var mapView = MapView(this)
        var mapViewController = act_select_area_rl_map_view as ViewGroup
        mapViewController.addView(mapView)


        act_select_area_sw_map.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {

//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.act_select_area_rl_map_view, MapsFragment()).commit()
                act_select_area_rl_map_view.visibility = View.VISIBLE
                act_select_area_rv_theaters.visibility = View.GONE
            } else {
                act_select_area_rl_map_view.visibility = View.GONE
                act_select_area_rv_theaters.visibility = View.VISIBLE
            }
        }

//        for(i in theaterList.indices){
//            addMarker(theaterList[i])
//        }
//
//
//
//    }
//    var marker_view = LayoutInflater.from(this).inflate(R.layout.activity_select_area, null)
//
//    fun setCustomMarkerView() {
//        var tag_marker = marker_view.findViewById(R.id.item_select_area_marker_tv_marker_1) as TextView
//    }
//
//    private fun addMarker(theater: Theater) {
//        var list : List<Theater>? = null
//        var markerOptions = MarkerO
//        if(theater.isSelectedMarker){
//            tag_marker
//        }
//    }
    }
}



