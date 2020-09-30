package com.ssafy.howdomodo.ui.selectArea

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.StoreDetail
import kotlinx.android.synthetic.main.item_store_detail.view.*

class StoreDetailAdapter() :
    RecyclerView.Adapter<StoreDetailViewHolder>() {
    private val storeDetailData = ArrayList<StoreDetail>()

    fun setStoreDetailData(newData: List<StoreDetail>) {
        with(storeDetailData) {
            clear()
            addAll(newData)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreDetailViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_store_detail, parent, false)
        return StoreDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreDetailViewHolder, position: Int) {
        holder.bind(storeDetailData[position])
    }

    override fun getItemCount(): Int {
        return storeDetailData.size
    }
}

class StoreDetailViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {



    fun bind(storeDetail: StoreDetail) {
        itemView.item_store_detail_tv_text.text = storeDetail.jong + " " + storeDetail.cnt + "회"
    }

}