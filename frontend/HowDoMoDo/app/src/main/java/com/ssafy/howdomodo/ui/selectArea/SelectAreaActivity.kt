package com.ssafy.howdomodo.ui.selectArea

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Store
import com.ssafy.howdomodo.data.datasource.model.StoreDetail
import com.ssafy.howdomodo.data.datasource.model.Theater
import com.ssafy.howdomodo.ui.theater.TheaterActivity
import kotlinx.android.synthetic.main.activity_select_area.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class SelectAreaActivity : AppCompatActivity() {

    companion object {
        val boolList = arrayListOf<Boolean>(false, false)
    }

    val viewModel: SelectAreaViewModel by viewModel()

    var theaterList = arrayListOf<Theater>(
        Theater(1246, 175, "브로드웨이(신사)", "서울특별시 강남구 논현동 도산대로 8길 8", "롯데시네마", 37.5164, 127.022),
        Theater(1247, 175, "도곡", "서울특별시 강남구 도곡동 174-3", "롯데시네마", 37.4875, 127.047),
        Theater(1248, 175, "청남씨네시티", "서울특별시 강남구 도산대로 323, 씨네시티빌딩 14층", "CGV", 37.5229, 127.037),
        Theater(1249, 175, "코엑스", "서울특별시 강남구 삼성1동 봉은사로 524", "메가박스", 37.5129, 127.057),
        Theater(1250, 175, "압구정", "서울특별시 강남구 신사동 압구정로30길 45", "CGV", 37.5243, 127.029),
        Theater(1251, 175, "de CHEF 압구정", "서울특별시 강남구 신사동 압구정로30길 45", "CGV", 37.5243, 127.029),
        Theater(1252, 175, "씨티(강남대로)", "서울특별시 강남구 역삼1동 강남대로 422", "메가박스", 37.5004, 127.027),
        Theater(1253, 175, "강남", "서울특별시 강남구 역삼동 강남대로 438", "CGV", 37.5016, 127.026),

        )

    val storeList = arrayListOf<Store>(
        Store(
            "식생활", 227, arrayListOf<StoreDetail>(
                StoreDetail("식료품점", "가게", 133),
                StoreDetail("식료품점", "가게", 133),
                StoreDetail("식료품점", "가게", 133),
                StoreDetail("식료품점", "가게", 133),
                StoreDetail("식료품점", "가게", 133),
                StoreDetail("식료품점", "가게", 133),
                StoreDetail("식료품점", "가게", 133),
                StoreDetail("식료품점", "가게", 133),
                StoreDetail("식료품점", "가게", 133),
                StoreDetail("식료품점", "가게", 133),
                StoreDetail("식료품점", "가게", 133),
                StoreDetail("식료품점", "가게", 133),
                StoreDetail("식료품점", "가게", 133),
                StoreDetail("식료품점", "가게", 133),
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

//    val regionData = arrayListOf<Region>(
//        Region(
//            "강원도",
//            arrayListOf(
//                City(101, "강릉시"),
//                City(102, "동해시"),
//                City(103, "속초시"),
//                City(104, "철원시")
//            )
//        ),
//        Region(
//            "경상남도",
//            arrayListOf(
//                City(105, "진주시"),
//                City(106, "창원시"),
//                City(107, "진해시"),
//                City(108, "김해시")
//            )
//        )
//    )


    lateinit var siDoAdapter: SidoAdapter
    lateinit var gugunAdapter: GugunAdapter
    lateinit var storeAdapter: StoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_area)

        boolList[0] = false
        boolList[1] = false

        setObserve()
        viewModel.getSiDo()
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
                    boolList[1] = false
                    setButtonActive()
                    viewModel.getGuGun(siDoAdapter.sidoData[position].name)
//                    gugunAdapter.setGuGunData(siDoAdapter.sidoData[position].guList)
//                    siDoAdapter.setClickOriginal(position)
                }

            })
//        siDoAdapter.setSidoData(regionData)
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
                    viewModel.getCardData(gugunAdapter.cityData[position].name)
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
//        storeAdapter.setStoreData(storeList)
        act_select_area_rv_store.adapter = storeAdapter
        act_select_area_rv_store.layoutManager = LinearLayoutManager(this)
        act_select_area_rv_store.setHasFixedSize(true)



        act_select_area_cl_btn_next.setOnClickListener {
            val intent = Intent(this, TheaterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setObserve() {
        viewModel.siDoData.observe(this, Observer {
            siDoAdapter.setSidoData(it)
        })

        viewModel.guGunData.observe(this, Observer {
            gugunAdapter.setGuGunData(it)
        })
        viewModel.cardData.observe(this, Observer {
            storeAdapter.setStoreData(it)
        })
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

