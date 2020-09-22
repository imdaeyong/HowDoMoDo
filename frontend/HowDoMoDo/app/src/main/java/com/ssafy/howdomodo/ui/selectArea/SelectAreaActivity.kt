package com.ssafy.howdomodo.ui.selectArea

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Gugun
import com.ssafy.howdomodo.data.datasource.model.Sido
import com.ssafy.howdomodo.data.datasource.model.Theater
import kotlinx.android.synthetic.main.activity_select_area.*

class SelectAreaActivity : AppCompatActivity() {

    //     var theaterList =  arrayListOf<Theater>()
    var theaterList = arrayListOf<Theater>(
        Theater("CGV", "강남1점", "5", "cgv"),
        Theater("CGV", "강남2점", "6", "cgv"),
        Theater("메가박스", "강남1점", "5", "megabox"),
        Theater("메가박스", "강남2점", "4", "megabox"),
        Theater("롯데시네마", "강남1점", "1", "lottecinema"),
        Theater("롯데시네마", "강남2점", "2", "lottecinema"),
        Theater("롯데시네마", "강남3점", "3", "lottecinema"),

        Theater("CGV", "강남1점", "5", "cgv"),
        Theater("CGV", "강남2점", "6", "cgv"),
        Theater("메가박스", "강남1점", "5", "megabox"),
        Theater("메가박스", "강남2점", "4", "megabox"),
        Theater("롯데시네마", "강남1점", "1", "lottecinema"),
        Theater("롯데시네마", "강남2점", "2", "lottecinema"),
        Theater("롯데시네마", "강남3점", "3", "lottecinema"),


        )

    var sidoList = arrayListOf<Sido>(
        Sido("서울특별시"),
        Sido("경기도"),
        Sido("충청남도"),
        Sido("충청북도"),
        Sido("부산"),
        Sido("강원도"),
        Sido("강원도"),
        Sido("강원도"),
        Sido("강원도"),
        Sido("강원도"),
        Sido("강원도"),
        Sido("강원도"),
        Sido("강원도"),

        )

    var gugunList = arrayListOf<Gugun>(
        Gugun("강남"),
        Gugun("강남대로"),
        Gugun("강동"),
        Gugun("군자"),
        Gugun("천호"),
        Gugun("신림"),
        Gugun("건국대입구"),
        Gugun("서울대입구"),
        Gugun("사당"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_area)


        //영화관 리스트
        val theaterAdapter = TheaterAdapter(this, theaterList) {
            //영화관 클릭 이벤트
                theater ->
            Toast.makeText(
                this,
                "선택하신 영화관은 ${theater.name} ${theater.kind} ${theater.distance}",
                Toast.LENGTH_SHORT
            ).show()

        }
        act_select_area_rv_theaters.adapter = theaterAdapter

        val lm = LinearLayoutManager(this)
        act_select_area_rv_theaters.layoutManager = lm
        act_select_area_rv_theaters.setHasFixedSize(true)

        //시도 리스트
        val sidoAdapter = SidoAdapter(this, sidoList) {
            //시도 클릭 이벤트
                sido ->
//            act_sido_tv_item.setBackgroundColor(Color.parseColor("f73859"))
//            act_sido_tv_item.setTextColor(Color.parseColor("ffffff"))
            Toast.makeText(this, "클릭한 도시는 ${sido.sido}", Toast.LENGTH_SHORT).show()
        }
        act_select_rv_sidos.adapter = sidoAdapter

        val lm2 = LinearLayoutManager(this)
        act_select_rv_sidos.layoutManager = lm2
        act_select_rv_sidos.setHasFixedSize(true)


        //구군 리스트
        val gugunAdapter = GugunAdapter(this, gugunList) { gugun ->
//            act_sido_tv_item.setBackgroundColor(Color.parseColor("f73859"))
//            act_sido_tv_item.setTextColor(Color.parseColor("ffffff"))
            Toast.makeText(this, "클릭한 지역은 ${gugun.gugun}", Toast.LENGTH_SHORT).show()
        }
        act_select_rv_guguns.adapter = gugunAdapter

        val lm3 = LinearLayoutManager(this)
        act_select_rv_guguns.layoutManager = lm3
        act_select_rv_guguns.setHasFixedSize(true)


    }
}