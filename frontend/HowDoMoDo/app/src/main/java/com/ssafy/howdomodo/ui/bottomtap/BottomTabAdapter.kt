package com.ssafy.howdomodo.ui.bottomtap

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ssafy.howdomodo.ui.Favorite.FavoriteFragment
import com.ssafy.howdomodo.ui.chat.ChatFragment
import com.ssafy.howdomodo.ui.main.MainFragment
import com.ssafy.howdomodo.ui.mypage.MypageFragment

class BottomTabAdapter(fm: FragmentManager, val fragmentCount: Int) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return MainFragment()
            1 -> return FavoriteFragment()
            2 -> return ChatFragment()
            3 -> return MypageFragment()
            else -> null!!
        }
    }

    override fun getCount(): Int = fragmentCount

}