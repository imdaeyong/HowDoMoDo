package com.ssafy.howdomodo.ui.selectArea

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
import net.daum.mf.map.api.CalloutBalloonAdapter
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class SelectAreaActivity : AppCompatActivity() {

    companion object {
        val boolList = arrayListOf<Boolean>(false, false, false)
    }

    var theaterList = arrayListOf<Theater>(
        Theater(1246, 175, "브로드웨이(신사)", "서울특별시 강남구 논현동 도산대로 8길 8", "롯데시네마",37.5164,127.022),
        Theater(1247, 175, "도곡", "서울특별시 강남구 도곡동 174-3", "롯데시네마",37.4875,127.047),
        Theater(1248,175,"청남씨네시티","서울특별시 강남구 도산대로 323, 씨네시티빌딩 14층","CGV",37.5229,127.037),
        Theater(1249,175,"코엑스","서울특별시 강남구 삼성1동 봉은사로 524","메가박스",37.5129,127.057),
        Theater(1250,175,"압구정","서울특별시 강남구 신사동 압구정로30길 45","CGV",37.5243,127.029),
        Theater(1251,175,"de CHEF 압구정","서울특별시 강남구 신사동 압구정로30길 45","CGV",37.5243,127.029),
        Theater(1252,175,"씨티(강남대로)","서울특별시 강남구 역삼1동 강남대로 422","메가박스",37.5004,127.027),
        Theater(1253,175,"강남","서울특별시 강남구 역삼동 강남대로 438","CGV",37.5016,127.026),

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
    lateinit var theaterAdapter: TheaterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_area)

        boolList[0] = false
        boolList[1] = false
        boolList[2] = false

        sidoAdapter = SidoAdapter(
            object : SidoAdapter.SidoViewHolder.SidoClickListener {
                override fun onclick(position: Int, textView: TextView) {
                    if (!sidoAdapter.getClicked(position)) {
                        if (sidoAdapter.getClickedSido() != -1) {
                            sidoAdapter.setClicked(sidoAdapter.getClickedSido(), false)
                        }
                        boolList[0] = true
                        act_select_rv_guguns.visibility = View.VISIBLE
                        sidoAdapter.setClicked(position, true)
                    } else if (sidoAdapter.getClicked(position)) {
                        sidoAdapter.setClicked(sidoAdapter.getClickedSido(), false)
                        boolList[0] = false
                        act_select_rv_guguns.visibility = View.

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
                        if (gugunAdapter.getClickedGugun() != -1) {
                            gugunAdapter.setClicked(gugunAdapter.getClickedGugun(), false)
                        }
                        boolList[1] = true
                        act_select_area_rv_theaters.visibility = View.VISIBLE
                        gugunAdapter.setClicked(position, true)
                    } else if (gugunAdapter.getClicked(position)) {
                        gugunAdapter.setClicked(gugunAdapter.getClickedGugun(), false)
                        boolList[1] = false
                        act_select_area_rv_theaters.visibility = View.INVISIBLE

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
                        if (theaterAdapter.getClickedTheater() != -1) {
                            theaterAdapter.setClicked(theaterAdapter.getClickedTheater(), false)
                        }
                        boolList[2] = true
                        theaterAdapter.setClicked(position, true)
                    } else if (theaterAdapter.getClicked(position)) {
                        ObjectMovie.movieTheater =
                            theaterAdapter.theaterData[position].theaterBrand + " " + theaterAdapter.theaterData[position].theaterName
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


        //Maps
        var mapView = MapView(this)
        var mapViewController = act_select_area_rl_map_view as ViewGroup
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.4874592, 127.0471432), true)
        mapView.setZoomLevel(5, true)


        for (i in theaterList.indices) {
            var t = theaterList[i]
            var marker = MapPOIItem()
            marker.itemName = t.theaterBrand + " " + t.theaterName
            marker.tag = t.theaterId
            marker.mapPoint = MapPoint.mapPointWithGeoCoord(t.theaterLat, t.theaterLng)
            marker.markerType = MapPOIItem.MarkerType.CustomImage
            marker.setCustomImageResourceId(R.drawable.cgv_marker_unselected)
            marker.userObject = t


            marker.selectedMarkerType = MapPOIItem.MarkerType.CustomImage
            marker.customSelectedImageResourceId = R.drawable.cgv_marker
            marker.isCustomImageAutoscale = false
            marker.setCustomImageAnchor(0.5f, 1.0f)

            mapView.setCalloutBalloonAdapter(CustomInfoWindow(context=this,theater=t))

            mapView.addPOIItem(marker)
            mapView.fitMapViewAreaToShowAllPOIItems()
        }

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

    class CustomInfoWindow(private val context: Context, val theater: Theater) :
        CalloutBalloonAdapter {
        lateinit var mCalloutBalloon: View
        var t = theater

        init {
            mCalloutBalloon =
                LayoutInflater.from(context).inflate(R.layout.item_custom_infowindow, null)
        }


        override fun getCalloutBalloon(poiItem: MapPOIItem): View {
            var theater_img = R.drawable.ic_launcher

            if (poiItem.itemName.contains("CGV")) {
                theater_img = R.drawable.cgv
            } else if (poiItem.itemName.contains("메가박스")) {
                theater_img = R.drawable.megabox
            } else if (poiItem.itemName.contains("롯데시네마")){
                theater_img = R.drawable.lottecinema
            }else {
                theater_img = R.drawable.ic_launcher
            }


            (mCalloutBalloon.findViewById<View>(R.id.item_infowindow_iv_theater_image) as ImageView).setImageResource(theater_img)
            (mCalloutBalloon.findViewById<View>(R.id.item_infowindow_tv_theater_title) as TextView).text = poiItem.itemName
            (mCalloutBalloon.findViewById<View>(R.id.item_infowindow_tv_theater_desc) as TextView).text = ((poiItem.userObject) as Theater).theaterAddress
            return mCalloutBalloon
        }

        override fun getPressedCalloutBalloon(poiItem: MapPOIItem): View {
            return mCalloutBalloon
        }

    }

}





