package com.ssafy.howdomodo.ui.selectArea

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Gugun
import com.ssafy.howdomodo.data.datasource.model.Sido
import com.ssafy.howdomodo.data.datasource.model.Theater
import kotlinx.android.synthetic.main.activity_select_area.*

class SelectAreaActivity : AppCompatActivity() {
    var theaterList = arrayListOf<Theater>(
        Theater("CGV", "강남1점", "5", "cgv", true),
        Theater("CGV", "강남2점", "6", "cgv", false),
        Theater("메가박스", "강남1점", "5", "megabox", false),
        Theater("메가박스", "강남2점", "4", "megabox", false),
        Theater("롯데시네마", "강남1점", "1", "lottecinema", false),
        Theater("롯데시네마", "강남2점", "2", "lottecinema", false),
        Theater("롯데시네마", "강남3점", "3", "lottecinema", false),

        Theater("CGV", "강남1점", "5", "cgv", false),
        Theater("CGV", "강남2점", "6", "cgv", false),
        Theater("메가박스", "강남1점", "5", "megabox", false),
        Theater("메가박스", "강남2점", "4", "megabox", false),
        Theater("롯데시네마", "강남1점", "1", "lottecinema", false),
        Theater("롯데시네마", "강남2점", "2", "lottecinema", false),
        Theater("롯데시네마", "강남3점", "3", "lottecinema", false),


        )

    var sidoList = arrayListOf<Sido>(
        Sido("서울특별시", false),
        Sido("경기도", true),
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
        Gugun("강남", true),
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

        sidoAdapter = SidoAdapter(
            object : SidoAdapter.SidoViewHolder.SidoClickListener {
                override fun onclick(position: Int, textView: TextView) {
                    if (!sidoAdapter.getClicked(position)) {
                        sidoAdapter.setClicked(sidoAdapter.getClickedSido(), false)
                        sidoAdapter.setClicked(position, true)
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
                        gugunAdapter.setClicked(gugunAdapter.getClickedGugun(), false)
                        gugunAdapter.setClicked(position, true)
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
                        theaterAdapter.setClicked(theaterAdapter.getClickedTheater(), false)
                        theaterAdapter.setClicked(position, true)
                    }
                }

            })
        theaterAdapter.setTheaterData(theaterList)
        act_select_area_rv_theaters.adapter = theaterAdapter
        var theaterlm = LinearLayoutManager(this)
        act_select_area_rv_theaters.layoutManager = theaterlm
        act_select_area_rv_theaters.setHasFixedSize(true)


    }
}
