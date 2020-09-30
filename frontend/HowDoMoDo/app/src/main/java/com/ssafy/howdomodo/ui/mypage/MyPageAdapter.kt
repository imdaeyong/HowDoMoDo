package com.ssafy.howdomodo.ui.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.`object`.UserCollection
import kotlinx.android.synthetic.main.fragment_mypage.view.*
import kotlinx.android.synthetic.main.item_my_page.view.*
import kotlinx.android.synthetic.main.mypage_top.view.*

class MyPageAdapter(private val clickListener: MyPageViewHolder.ClickListener) :
    RecyclerView.Adapter<MyPageViewHolder>() {

    val mineList =
        listOf<String>("로그아웃", "오픈소스 라이선스", "이용약관", "개인정보처리방침", "회원탈퇴")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_my_page, parent, false)
        return MyPageViewHolder(inflatedView, clickListener)
    }

    override fun getItemCount(): Int {
        return mineList.size
    }

    override fun onBindViewHolder(holder: MyPageViewHolder, position: Int) {
        holder.bind(mineList[position])
    }
}

class MyPageViewHolder(itemView: View, private val clickListener: ClickListener) :
    RecyclerView.ViewHolder(itemView) {

    interface ClickListener {
        fun itemClick(position: Int)
    }

    init {
        itemView.item_my_page_iv_go.setOnClickListener {
            clickListener.itemClick(adapterPosition)
        }
    }

    fun bind(data: String) {
        itemView.item_my_page_tv_text.text = data
    }

}
