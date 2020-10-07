package com.ssafy.howdomodo.ui.selectArea

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Action
import kotlinx.android.synthetic.main.item_store.view.*

class StoreAdapter(private val downClickListener: StoreViewHolder.OnClickListener) :
    RecyclerView.Adapter<StoreViewHolder>() {

    val storeData = ArrayList<Action>()

    fun setStoreData(newData: List<Action>) {
        with(storeData) {
            clear()
            addAll(newData)
        }
        notifyDataSetChanged()
    }

    fun setClicked(isClicked: Boolean, position: Int) {
        storeData[position].isClicked = isClicked
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

class StoreViewHolder(itemView: View, private val downClickListener: OnClickListener) :
    RecyclerView.ViewHolder(itemView) {

    interface OnClickListener {
        fun downUpClick(recyclerView: RecyclerView, position: Int, downImage: ImageView)
    }

    init {
        itemView.item_store_iv_up_down.setOnClickListener {
            downClickListener.downUpClick(
                itemView.item_store_rv_down,
                adapterPosition,
                itemView.item_store_iv_up_down
            )
        }
    }

    lateinit var storeDetailAdapter: StoreDetailAdapter

    fun bind(store: Action) {
        when (store.kinds) {
            "식생활" -> itemView.item_store_iv_image.setImageResource(R.drawable.iv_store_food)
            "쇼핑" -> itemView.item_store_iv_image.setImageResource(R.drawable.iv_store_shop)
            "미용" -> itemView.item_store_iv_image.setImageResource(R.drawable.iv_store_hair)
            "유흥" -> itemView.item_store_iv_image.setImageResource(R.drawable.iv_store_club)
            "레포츠/문화/취미" -> itemView.item_store_iv_image.setImageResource(R.drawable.iv_store_sport)
        }
        itemView.item_store_tv_title.text = store.kinds
        itemView.item_store_tv_cnt.text = "사용횟수 ${store.totalCnt}회"

        storeDetailAdapter = StoreDetailAdapter()
        storeDetailAdapter.setStoreDetailData(store.down)
        itemView.item_store_rv_down.adapter = storeDetailAdapter
        itemView.item_store_rv_down.layoutManager = LinearLayoutManager(itemView.context)

    }
}