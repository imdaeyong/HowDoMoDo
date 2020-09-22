package com.ssafy.howdomodo.ui.selectArea

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Sido

class SidoAdapter(val context: Context, val sidoList: ArrayList<Sido>, val sidoClick: (Sido) -> Unit) :
        RecyclerView.Adapter<SidoAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_sido, parent, false)
        return Holder(view, sidoClick)
    }

    override fun getItemCount(): Int {
        return sidoList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(sidoList[position], context)
    }

    inner class Holder(itemView: View,itemClick: (Sido) -> Unit) : RecyclerView.ViewHolder(itemView) {
        var sidotv = itemView?.findViewById<TextView>(R.id.item_sido_tv_city)

        fun bind(sido: Sido, context: Context) {
            sidotv?.text = sido.sido
            itemView.setOnClickListener{
                sidoClick(sido)
            }

        }
    }
}
