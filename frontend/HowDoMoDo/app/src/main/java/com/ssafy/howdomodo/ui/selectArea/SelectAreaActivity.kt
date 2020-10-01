package com.ssafy.howdomodo.ui.selectArea

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.*
import com.ssafy.howdomodo.ui.gwanSelect.GwanSelectActivity
import com.ssafy.howdomodo.ui.theater.TheaterActivity
import kotlinx.android.synthetic.main.activity_select_area.*


class SelectAreaActivity : AppCompatActivity() {

    companion object {
        val boolList = arrayListOf<Boolean>(false, false)
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

    val storeList = arrayListOf<Store>(
        Store(
            "식생활", 227, arrayListOf<StoreDetail>(
                StoreDetail("식료품점", "가게", 133),
                StoreDetail("레스토랑", "식당", 154)
            )
        ),
        Store(
            "의생활", 135, arrayListOf<StoreDetail>(
                StoreDetail("양복점", "가게", 444),
                StoreDetail("기성복점", "아이들", 42)
            )
        )
    )

    val regionData = arrayListOf<Region>(
        Region(
            "강원도",
            arrayListOf(
                City(101, "강릉시"),
                City(102, "동해시"),
                City(103, "속초시"),
                City(104, "철원시")
            )
        ),
        Region(
            "경상남도",
            arrayListOf(
                City(105, "진주시"),
                City(106, "창원시"),
                City(107, "진해시"),
                City(108, "김해시")
            )
        )
    )


    lateinit var siDoAdapter: SidoAdapter
    lateinit var gugunAdapter: GugunAdapter
    lateinit var storeAdapter: StoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_area)

        boolList[0] = false
        boolList[1] = false

        siDoAdapter = SidoAdapter(
            object : SidoAdapter.SidoViewHolder.SidoClickListener {
                override fun onclick(position: Int, textView: TextView) {
                    if (!siDoAdapter.getClicked(position)) {
                        if (siDoAdapter.getClickedSido() != -1) {
                            siDoAdapter.setClicked(siDoAdapter.getClickedSido(), false)
                        }
                        boolList[0] = true
                        act_select_rv_guguns.visibility = View.VISIBLE
                        act_select_area_rv_store.visibility = View.INVISIBLE
                        siDoAdapter.setClicked(position, true)
                    } else if (siDoAdapter.getClicked(position)) {
                        siDoAdapter.setClicked(siDoAdapter.getClickedSido(), false)
                        boolList[0] = false
                        act_select_rv_guguns.visibility = View.INVISIBLE
                        act_select_area_rv_store.visibility = View.INVISIBLE
                        act_select_area_ll_third_bar.setBackgroundColor(Color.parseColor("#EEEEEE"))
                    }
                    setButtonActive()
                    gugunAdapter.setGuGunData(siDoAdapter.sidoData[position].guList)
                    siDoAdapter.setClickOriginal(position)
                }

            })
        siDoAdapter.setSidoData(regionData)
        act_select_rv_sidos.adapter = siDoAdapter
        act_select_rv_sidos.layoutManager = LinearLayoutManager(this)
        act_select_rv_sidos.setHasFixedSize(true)


        gugunAdapter = GugunAdapter(
            object : GugunViewHolder.GugunClickListener {
                override fun onclick(position: Int, textView: TextView) {
                    if (!gugunAdapter.getClicked(position)) {
                        if (gugunAdapter.getClickedGuGun() != -1) {
                            gugunAdapter.setClicked(gugunAdapter.getClickedGuGun(), false)
                        }
                        boolList[1] = true
                        act_select_area_rv_store.visibility = View.VISIBLE
                        act_select_area_ll_third_bar.setBackgroundColor(Color.parseColor("#FFFFFF"))
                        gugunAdapter.setClicked(position, true)
                    } else if (gugunAdapter.getClicked(position)) {
                        gugunAdapter.setClicked(gugunAdapter.getClickedGuGun(), false)
                        boolList[1] = false
                        act_select_area_rv_store.visibility = View.INVISIBLE
                        act_select_area_ll_third_bar.setBackgroundColor(Color.parseColor("#EEEEEE"))

                    }
                    setButtonActive()
                }

            })
        act_select_rv_guguns.adapter = gugunAdapter
        act_select_rv_guguns.layoutManager = LinearLayoutManager(this)
        act_select_rv_guguns.setHasFixedSize(true)

        storeAdapter = StoreAdapter(object : StoreViewHolder.OnClickListener {
            override fun downUpClick(
                recyclerView: RecyclerView,
                position: Int,
                downImage: ImageView
            ) {
                if (!storeAdapter.storeData[position].isClicked) {
                    storeAdapter.setClicked(true, position)
                    downImage.setImageResource(R.drawable.btn_up)
                    recyclerView.visibility = View.VISIBLE
                } else {
                    storeAdapter.setClicked(false, position)
                    downImage.setImageResource(R.drawable.btn_down)
                    recyclerView.visibility = View.GONE
                }
            }
        })
        storeAdapter.setStoreData(storeList)


//        theaterAdapter = TheaterAdapter(
//            object : TheaterAdapter.TheaterViewHolder.TheaterClickListener {
//                override fun onclick(position: Int, textView: TextView) {
//                    if (!theaterAdapter.getClicked(position)) {
//                        if (theaterAdapter.getClickedTheater() != -1) {
//                            theaterAdapter.setClicked(theaterAdapter.getClickedTheater(), false)
//                        }
//                        boolList[2] = true
//                        theaterAdapter.setClicked(position, true)
//                    } else if (theaterAdapter.getClicked(position)) {
//                        ObjectMovie.movieTheater =
//                            theaterAdapter.theaterData[position].kind + " " + theaterAdapter.theaterData[position].name
//                        theaterAdapter.setClicked(theaterAdapter.getClickedTheater(), false)
//                        boolList[2] = false
//                    }
//                    setButtonActive()
//                }
//
//            })
//        theaterAdapter.setTheaterData(theaterList)
        act_select_area_rv_store.adapter = storeAdapter
        act_select_area_rv_store.layoutManager = LinearLayoutManager(this)
        act_select_area_rv_store.setHasFixedSize(true)


//        //Maps
//        var mapView = MapView(this)
//        var mapViewController = act_select_area_rl_map_view as ViewGroup
//        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.4874592, 127.0471432), true)
//        mapView.setZoomLevel(5, true)
//
//
//        for (i in theaterList.indices) {
//            var t = theaterList[i]
//            var marker = MapPOIItem()
//            marker.itemName = t.kind + " " + t.name
//            marker.tag = 0
//            marker.mapPoint = MapPoint.mapPointWithGeoCoord(t.theater_lat, t.theater_lng)
//            marker.markerType = MapPOIItem.MarkerType.CustomImage
//            marker.setCustomImageResourceId(R.drawable.cgv_marker_unselected)
//
//            marker.selectedMarkerType = MapPOIItem.MarkerType.CustomImage
//            marker.customSelectedImageResourceId = R.drawable.cgv_marker
//            marker.isCustomImageAutoscale=false
//            marker.setCustomImageAnchor(0.5f, 1.0f)
//
//            mapView.setCalloutBalloonAdapter(CustomInfoWindow(context = this))
//
//            mapView.addPOIItem(marker)
//            mapView.fitMapViewAreaToShowAllPOIItems()
//        }
//
//        mapViewController.addView(mapView)
//
//
//        act_select_area_sw_map.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) {
//
////                supportFragmentManager.beginTransaction()
////                    .replace(R.id.act_select_area_rl_map_view, MapsFragment()).commit()
//                act_select_area_rl_map_view.visibility = View.VISIBLE
//                act_select_area_rv_theaters.visibility = View.GONE
//            } else {
//                act_select_area_rl_map_view.visibility = View.GONE
//                act_select_area_rv_theaters.visibility = View.VISIBLE
//            }
//        }


        act_select_area_cl_btn_next.setOnClickListener {
            val intent = Intent(this, TheaterActivity::class.java)
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
//    class CustomInfoWindow(private val context: Context) : CalloutBalloonAdapter {
//        lateinit var mCalloutBalloon: View
//
//        init {
//            mCalloutBalloon = LayoutInflater.from(context).inflate(R.layout.item_custom_infowindow, null)
//        }
//
//        fun CustomInfoWindow() {
//
//        }
//
//
//        override fun getCalloutBalloon(poiItem: MapPOIItem): View {
//            (mCalloutBalloon.findViewById<View>(R.id.item_infowindow_iv_theater_image) as ImageView).setImageResource(
//                R.drawable.ic_launcher
//            )
//            (mCalloutBalloon.findViewById<View>(R.id.item_infowindow_tv_theater_title) as TextView).text =
//                poiItem.itemName
//            (mCalloutBalloon.findViewById<View>(R.id.item_infowindow_tv_theater_desc) as TextView).text =
//                "Custom CalloutBalloon"
//            Log.e("hihi",poiItem.toString())
//            return mCalloutBalloon
//        }
//
//        override fun getPressedCalloutBalloon(poiItem: MapPOIItem): View {
//            return mCalloutBalloon
//        }
//
//    }

}

