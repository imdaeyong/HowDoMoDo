package com.ssafy.howdomodo.ui.selectArea

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Gugun

class GugunAdapter(val context: Context, val gugunList: ArrayList<Gugun>, val gugunClick: (Gugun) -> Unit) :
        RecyclerView.Adapter<GugunAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_gugun, parent, false)
        return Holder(view, gugunClick)
    }

    override fun getItemCount(): Int {
        return gugunList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(gugunList[position], context)
    }

    inner class Holder(itemView: View,itemClick: (Gugun) -> Unit) : RecyclerView.ViewHolder(itemView) {
        var guguntv = itemView?.findViewById<TextView>(R.id.act_gugun_tv_item)

        fun bind(gugun : Gugun, context: Context) {
            guguntv?.text = gugun.gugun

            itemView.setOnClickListener{
                gugunClick(gugun)
            }

        }
    }
}
