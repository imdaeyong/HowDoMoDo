package com.ssafy.howdomodo.ui.selectArea

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Store
import kotlinx.android.synthetic.main.item_store.view.*

class StoreAdapter(private val downClickListener: StoreViewHolder.DownClickListener) :
    RecyclerView.Adapter<StoreViewHolder>() {

     val storeData = ArrayList<Store>()

    fun setStoreData(newData: List<Store>) {
        with(storeData) {
            clear()
            addAll(newData)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_store, parent, false)
        return StoreViewHolder(view, downClickListener)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(storeData[position])
    }

    override fun getItemCount(): Int {
        return storeData.size
    }
}

class StoreViewHolder(itemView: View, private val downClickListener: DownClickListener) :
    RecyclerView.ViewHolder(itemView) {

    interface DownClickListener {
        fun onClick(recyclerView: RecyclerView, position: Int,downImage:ImageView)
    }

    init {
        itemView.item_store_iv_up_down.setOnClickListener {
            downClickListener.onClick(itemView.item_store_rv_down, adapterPosition,itemView.item_store_iv_up_down)
        }
    }

    lateinit var storeDetailAdapter: StoreDetailAdapter

    fun bind(store: Store) {
        itemView.item_store_tv_title.text = store.kinds
        itemView.item_store_tv_cnt.text = "사용횟수 ${store.totalCnt}회"

        storeDetailAdapter = StoreDetailAdapter()
        storeDetailAdapter.setStoreDetailData(store.down)
        itemView.item_store_rv_down.adapter = storeDetailAdapter
        itemView.item_store_rv_down.layoutManager = LinearLayoutManager(itemView.context)


    }
}