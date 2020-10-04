package com.ssafy.howdomodo.ui.selectArea

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Region
import kotlinx.android.synthetic.main.item_sido.view.*

class SidoAdapter(private val onclick: SidoViewHolder.SidoClickListener) :
    RecyclerView.Adapter<SidoAdapter.SidoViewHolder>() {

    val sidoData = ArrayList<Region>()


//    fun setClickOriginal(position: Int){
//        val cityList = sidoData[position].guList
//        for(i in cityList.indices){
//            cityList[i].isClicked = false
//        }
//        notifyDataSetChanged()
//    }

    fun setSidoData(newData: List<Region>) {
        with(sidoData) {
            clear()
            addAll(newData)
        }
        notifyDataSetChanged()
    }

    fun getClicked(position: Int): Boolean {
        return sidoData[position].isClicked
    }

    fun getClickedSido(): Int {
        for (i in sidoData.indices) {
            if (sidoData[i].isClicked) {
                return i
            }
        }
        return -1
    }

    fun setClicked(position: Int, status: Boolean) {
        sidoData[position].isClicked = status

        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SidoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sido, parent, false)
        return SidoViewHolder(view, onclick)
    }

    override fun getItemCount(): Int {
        return sidoData.size
    }

    override fun onBindViewHolder(holder: SidoViewHolder, position: Int) {
        holder.bind(sidoData[position])
    }

    class SidoViewHolder(
        itemView: View,
        private val sidoClickListener: SidoClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        interface SidoClickListener {
            fun onclick(position: Int, textView: TextView)
        }

        init {
            itemView.item_sido_cl_box.setOnClickListener {
                sidoClickListener.onclick(adapterPosition, itemView.item_sido_tv_city)
            }
        }

        fun bind(data: Region) {
            var sido = data.name
            itemView.item_sido_tv_city.text = sido
            if (data.isClicked) {
                itemView.item_sido_cl_box.setBackgroundResource(R.drawable.item_sido_selected)
                itemView.item_sido_tv_city.setTextColor(Color.parseColor("#fff4eb"))
            } else {
                itemView.item_sido_cl_box.setBackgroundResource(R.drawable.item_sido_unselected)
                itemView.item_sido_tv_city.setTextColor(Color.parseColor("#333333"))
            }

        }
    }
}
