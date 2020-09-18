package com.ssafy.howdomodo.ui.bottomtap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.tabs.TabLayout
import com.ssafy.howdomodo.R
import kotlinx.android.synthetic.main.activity_bottom_tab.*


class BottomTabActivity : AppCompatActivity() {
    var flag = 0
    var token = ""
    var myUniv = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_tab)

        flag = intent.getIntExtra("alertFlag", 2)
        setAdapter()
        setTabBar()
        if (flag == 1) bottom_vp.currentItem = 3


//        bottom_vp.setOnPageChangeListener(object : OnPageChangeListener {
//            override fun onPageScrollStateChanged(state: Int) {
//            }
//
//            override fun onPageScrolled(
//                position: Int,
//                positionOffset: Float,
//                positionOffsetPixels: Int
//            ) {
//            }
//
//            override fun onPageSelected(position: Int) {
//                if(position==0){
//                    HomeFragment().userVisibleHint = true
//                }
//            }
//        })
    }

    private fun setTabBar() {
        val bottomTabBar: View = LayoutInflater.from(this).inflate(R.layout.tab_layout, null)
        bottom_tab_layout.run {

            addTab(
                    this.newTab()
                            .setCustomView(bottomTabBar.findViewById(R.id.tab_cl_home) as ConstraintLayout)
            )
            addTab(
                    this.newTab()
                            .setCustomView(bottomTabBar.findViewById(R.id.tab_cl_store) as ConstraintLayout)
            )
            addTab(
                    this.newTab()
                            .setCustomView(bottomTabBar.findViewById(R.id.tab_cl_history) as ConstraintLayout)
            )
            addTab(
                    this.newTab()
                            .setCustomView(bottomTabBar.findViewById(R.id.tab_cl_my) as ConstraintLayout)
            )

            // 인디케이터 없애기
            setSelectedTabIndicator(0)
        }
    }

    private fun setAdapter() {
        bottom_vp.adapter =
                BottomTabAdapter(
                        supportFragmentManager,
                        4
                )

        bottom_vp.offscreenPageLimit = 3
        bottom_vp.currentItem = 0

        bottom_vp.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(bottom_tab_layout))

        bottom_tab_layout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                bottom_vp!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }

}
