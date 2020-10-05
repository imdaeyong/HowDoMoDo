package com.ssafy.howdomodo.ui.theater

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.`object`.ObjectMovie
import com.ssafy.howdomodo.`object`.TheaterCollection
import com.ssafy.howdomodo.`object`.UserCollection
import com.ssafy.howdomodo.data.datasource.model.Theater
import com.ssafy.howdomodo.ui.favorite.FavoritesViewModel
import com.ssafy.howdomodo.ui.gwanSelect.GwanSelectActivity
import kotlinx.android.synthetic.main.activity_theater.*
import net.daum.mf.map.api.CalloutBalloonAdapter
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapView.POIItemEventListener
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class TheaterActivity : AppCompatActivity(), POIItemEventListener{
    private val getTheatersViewModel: GetTheatersViewModel by viewModel()
    private val favoritesViewModel: FavoritesViewModel by viewModel()

    companion object {
        var theater_select = false
        var selectSiName = "서울특별시"
        var selectGuName = "강남구"
        var userCode = 658903366
        lateinit var selectedTheater: Theater
        lateinit var mapView: MapView
        var theaterList = ArrayList<Theater>()
        var cur_lat = 99.999
        var cur_lng = 99.999
    }

    //    var theaterList = arrayListOf<Theater>(
//        Theater(1246, 175, "브로드웨이(신사)", "서울특별시 강남구 논현동 도산대로 8길 8", "롯데시네마", 37.5164, 127.022),
//        Theater(1247, 175, "도곡", "서울특별시 강남구 도곡동 174-3", "롯데시네마", 37.4875, 127.047),
//        Theater(1248, 175, "청남씨네시티", "서울특별시 강남구 도산대로 323, 씨네시티빌딩 14층", "CGV", 37.5229, 127.037),
//        Theater(1249, 175, "코엑스", "서울특별시 강남구 삼성1동 봉은사로 524", "메가박스", 37.5129, 127.057),
//        Theater(1250, 175, "압구정", "서울특별시 강남구 신사동 압구정로30길 45", "CGV", 37.5243, 127.029),
//        Theater(1251, 175, "de CHEF 압구정", "서울특별시 강남구 신사동 압구정로30길 45", "CGV", 37.5243, 127.029),
//        Theater(1252, 175, "씨티(강남대로)", "서울특별시 강남구 역삼1동 강남대로 422", "메가박스", 37.5004, 127.027),
//        Theater(1253, 175, "강남", "서울특별시 강남구 역삼동 강남대로 438", "CGV", 37.5016, 127.026),
//    )
    lateinit var theaterAdapter: TheaterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theater)

        if(UserCollection.userCode!=null &&UserCollection.userCode!="") {
            userCode = UserCollection.userCode.toInt()
        }
            //해당 지역이 뭔지 받아오기.
        if (intent.hasExtra("siName")) {
            selectSiName = intent.getStringExtra("siName")!!
            selectGuName = intent.getStringExtra("guName")!!
            Toast.makeText(
                this@TheaterActivity,
                intent.getStringExtra("siName") + intent.getStringExtra("guName"),
                Toast.LENGTH_SHORT
            )
                .show()
        } else {
            Log.e(
                "유사에러",
                "이전 페이지에서 넘어온 시도데이터가 없음 - > 디폴트 검색!" + selectSiName + selectGuName + userCode
            )
        }

        //현재위치 불러오기.
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (Build.VERSION.SDK_INT >= 23 &&
            ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@TheaterActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                0
            )
        } else {
            when {
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) -> {
                    var current_location =
                        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    cur_lat = current_location?.latitude!!
                    cur_lng = current_location.longitude

                }
            }
        }

        act_theater_tv_search.text = selectSiName + " " + selectGuName
//            act_theater_tv_search.text = cur_lat.toString() + " " + cur_lng.toString()
//            act_theater_tv_search.setOnClickListener(
//                View.OnClickListener {
//
//                        Log.i("click","infowindow클릭이벤트")
//
//                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("kakaomap://route?sp=37.537229,127.005515&ep=37.4979502,127.0276368&by=CAR"))
//                        startActivity(intent)
//                    }
//
//                )

        getTheatersViewModel.getTheaters(selectSiName, selectGuName, userCode)

        observeData()

        act_theater_cl_theater_selected.setOnClickListener {
            val intent = Intent(this, GwanSelectActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeData() {
        getTheatersViewModel.getTheatersError.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
        getTheatersViewModel.getTheatersResponse.observe(this, Observer {
            //영화 리스트 가져왔을떄.
            if (it.status == 200) {
                Toast.makeText(this, "영화관 리스트 출력!", Toast.LENGTH_SHORT).show()

                theaterAdapter = TheaterAdapter(
                    object :
                        TheaterAdapter.TheaterViewHolder.TheaterClickListener {
                        override fun onclick(position: Int, textView: TextView) {
                            if (!theaterAdapter.getClicked(position)) {
                                if (theaterAdapter.getClickedTheater() != -1) {
                                    theaterAdapter.setClicked(
                                        theaterAdapter.getClickedTheater(),
                                        false
                                    )
                                }
                                theaterAdapter.setClicked(position, true)
                                theater_select = true
                                selectedTheater = theaterList[position]
                                changeCenter()

                            } else if (theaterAdapter.getClicked(position)) {
                                ObjectMovie.movieTheater =
                                    theaterAdapter.theaterData[position].theaterBrand + " " + theaterAdapter.theaterData[position].theaterName
                                theaterAdapter.setClicked(theaterAdapter.getClickedTheater(), false)
                                theater_select = false
                            }
                            setButtonActive()
                        }

                        override fun starClick(position: Int, starImageView: ImageView) {
                            Toast.makeText(
                                this@TheaterActivity,
                                position.toString(),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            theaterList[position].fav = !theaterList[position].fav
                            isStarCheck(position, starImageView)
                        }
                    })
                theaterList = it.data!!
                theaterAdapter.setTheaterData(theaterList)
                act_theater_rv_theaters.adapter = theaterAdapter
                var theaterlm = LinearLayoutManager(this)
                act_theater_rv_theaters.layoutManager = theaterlm
                act_theater_rv_theaters.setHasFixedSize(true)

                //Maps
                mapView = MapView(this)
                var mapViewController = act_theater_rl_map_view as ViewGroup
                mapView.setMapCenterPoint(
                    MapPoint.mapPointWithGeoCoord(37.4874592, 127.0471432),
                    true
                )
                mapView.setZoomLevel(7, true)

                for (i in theaterList.indices) {
                    var t = theaterList[i]
                    var marker_img = R.drawable.ic_launcher
                    var selected_marker_img = R.drawable.ic_launcher

                    if (t.theaterBrand.contains("CGV")) {
                        marker_img = R.drawable.cgv_marker_unselected
                        selected_marker_img = R.drawable.cgv_marker
                    } else if (t.theaterBrand.contains("메가박스")) {
                        marker_img = R.drawable.megabox_marker_unselected
                        selected_marker_img = R.drawable.megabox_marker
                    } else if (t.theaterBrand.contains("롯데시네마")) {
                        marker_img = R.drawable.lotte_marker_unselected
                        selected_marker_img = R.drawable.lotte_marker
                    } else {
                        marker_img = R.drawable.cgv_marker_unselected
                        selected_marker_img = R.drawable.cgv_marker
                    }

                    var marker = MapPOIItem()
                    marker.itemName = t.theaterBrand + " " + t.theaterName
                    marker.tag = t.theaterId
                    marker.mapPoint = MapPoint.mapPointWithGeoCoord(t.theaterLat, t.theaterLon)
                    marker.markerType = MapPOIItem.MarkerType.CustomImage
                    marker.customImageResourceId = marker_img
                    marker.userObject = t


                    marker.selectedMarkerType = MapPOIItem.MarkerType.CustomImage
                    marker.customSelectedImageResourceId = selected_marker_img
                    marker.isCustomImageAutoscale = false
                    marker.setCustomImageAnchor(0.5f, 1.0f)

                    mapView.setCalloutBalloonAdapter(
                        CustomInfoWindow(
                            context = this,
                            theater = t,
                            m = marker
                        )
                    )
                    mapView.addPOIItem(marker)
                }
                mapView.fitMapViewAreaToShowAllPOIItems()
                mapView.setPOIItemEventListener(this)

                mapViewController.addView(mapView)


            } else {
                Toast.makeText(this, "TheaterActivity observeData 에러!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun changeCenter() {
        mapView.setMapCenterPoint(
            MapPoint.mapPointWithGeoCoord(
                selectedTheater.theaterLat,
                selectedTheater.theaterLon
            ), true
        )
    }

    fun isStarCheck(position: Int, stariv: ImageView) {
        var t = theaterList[position]
        if (t.fav) {
            var favoriteJson = JSONObject()
            favoriteJson.put("userCode", userCode)
            favoriteJson.put("theaterId", t.theaterId)
            favoriteJson.put("theaterName", t.theaterName)
            favoriteJson.put("theaterBrand", t.theaterBrand)

            var body = JsonParser.parseString(favoriteJson.toString()) as JsonObject

            Log.e("isstarcheck", body.toString())

            favoritesViewModel.favoritesAdd(body)
            stariv.setImageResource(R.drawable.star_clicked)
        } else {
            favoritesViewModel.favoritesDelete(userCode, t.theaterId)
            stariv.setImageResource(R.drawable.star_unclicked)
        }
    }

    fun setButtonActive() {
        if (theater_select) {
            act_theater_cl_theater_selected.setBackgroundColor(Color.parseColor("#f73859"))
            var theaterName = selectedTheater.theaterBrand+" "+ selectedTheater.theaterName

            TheaterCollection.mvTheater = theaterName

            if(selectedTheater.theaterBrand.contains("메가박스")) {
                TheaterCollection.mvTheaterName = "mega"
            }else if(selectedTheater.theaterBrand.contains("롯데시네마")){
                TheaterCollection.mvTheaterName = "lotte"
            }else {
                TheaterCollection.mvTheaterName = "cgv"
            }
            act_theater_cl_theater_selected.isClickable = true

        } else {
            act_theater_cl_theater_selected.setBackgroundColor(Color.parseColor("#aaaaaa"))
            act_theater_cl_theater_selected.isClickable = false
        }
    }

    class CustomInfoWindow(private val context: Context, val theater: Theater, var m: MapPOIItem) :
        CalloutBalloonAdapter {
        var mCalloutBalloon: View

        init {
            mCalloutBalloon =
                LayoutInflater.from(context).inflate(R.layout.item_custom_infowindow, null)

            mCalloutBalloon.findViewById<View>(R.id.item_infowindow_btn_find_way_bycar).setOnClickListener(
                View.OnClickListener {
                    Log.i("click","infowindow클릭이벤트")

                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("kakaomap://route?sp="+"37.537229"+","+"127.005515"+"&ep="+"37.4979502"+","+"127.0276368"+"&by=CAR"))
                    this.context.startActivity(intent)
                }

            )

        }


        override fun getCalloutBalloon(poiItem: MapPOIItem): View {
            var theater_img: Int

            if (poiItem.itemName.contains("CGV")) {
                theater_img = R.drawable.cgv
            } else if (poiItem.itemName.contains("메가박스")) {
                theater_img = R.drawable.megabox
            } else if (poiItem.itemName.contains("롯데시네마")) {
                theater_img = R.drawable.lottecinema
            } else {
                theater_img = R.drawable.cgv
            }


            (mCalloutBalloon.findViewById<View>(R.id.item_infowindow_iv_theater_image) as ImageView).setImageResource(
                theater_img
            )
            (mCalloutBalloon.findViewById<View>(R.id.item_infowindow_tv_theater_title) as TextView).text =
                poiItem.itemName

            (mCalloutBalloon.findViewById<View>(R.id.item_infowindow_tv_theater_desc) as TextView).text =
                ((poiItem.userObject) as Theater).theaterAddress

            return mCalloutBalloon
        }

        override fun getPressedCalloutBalloon(poiItem: MapPOIItem): View {
            return mCalloutBalloon
        }
    }

    override fun onCalloutBalloonOfPOIItemTouched(mapView: MapView?, mappoiItem: MapPOIItem?) {
        Toast.makeText(    this,
            "Clicked " + mappoiItem?.getItemName() + " Callout Balloon",
            Toast.LENGTH_SHORT
        ).show()

    }

    override fun onCalloutBalloonOfPOIItemTouched(
            mapView: MapView?,
            p1: MapPOIItem?,
                p2: MapPOIItem.CalloutBalloonButtonType?
        ) {
        var end_lat = p1?.mapPoint?.mapPointGeoCoord?.latitude
        var end_lng = p1?.mapPoint?.mapPointGeoCoord?.longitude

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("kakaomap://route?sp="+ cur_lat+","+ cur_lng+"&ep="+end_lat+","+end_lng +"&by=CAR"))
        startActivity(intent)

        Toast.makeText(    this,
            "카카오맵으로 연결 " + p1?.itemName + "길찾기",
            Toast.LENGTH_SHORT
        ).show()

    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {
    }

    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
    }

}
