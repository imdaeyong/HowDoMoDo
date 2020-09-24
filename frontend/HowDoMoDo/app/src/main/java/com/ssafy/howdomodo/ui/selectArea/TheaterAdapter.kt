package com.ssafy.howdomodo.ui.selectArea

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Theater

class TheaterAdapter(val context: Context, val theaterList: ArrayList<Theater>, var theaterClick :(Theater)->Unit) :
    RecyclerView.Adapter<TheaterAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_theater,parent,false)
        return Holder(view,theaterClick)
    }

    override fun getItemCount(): Int {
        return theaterList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(theaterList[position],context)
    }

    inner class Holder(itemView: View,theaterClick: (Theater) -> Unit) : RecyclerView.ViewHolder(itemView) {
        var photo = itemView?.findViewById<ImageView>(R.id.act_theater_area_iv_photo)
        val kind = itemView?.findViewById<TextView>(R.id.act_theater_area_tv_kind)
        var name = itemView?.findViewById<TextView>(R.id.act_theater_area_tv_name)
        var distance = itemView?.findViewById<TextView>(R.id.act_theater_area_tv_distance)

        var bt = itemView?.findViewById<TextView>(R.id.act_theater_area_tv_distance)


        fun bind(theater: Theater, context: Context) {
            if (theater.photo != "") {
                val resourceId = context.resources.getIdentifier(theater.photo, "drawable", context.packageName)
                photo?.setImageResource(resourceId)
            } else {
                photo?.setImageResource(R.mipmap.ic_launcher)
            }

            kind?.text = theater.kind
            name?.text = theater.name
            distance?.text = theater.distance

            itemView.setOnClickListener {
                theaterClick(theater)
            }
        }
    }
}
