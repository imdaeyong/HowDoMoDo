package com.ssafy.howdomodo.ui.selectArea

import android.content.Context
import android.media.Image
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
        var photo = itemView?.findViewById<ImageView>(R.id.item_select_area_theater_iv_photo)
        var name = itemView?.findViewById<TextView>(R.id.item_select_area_theater_tv_name)
        var distance = itemView?.findViewById<TextView>(R.id.item_select_area_theater_tv_distance)

        var booking = itemView?.findViewById<ImageView>(R.id.item_select_area_theater_iv_booking)


        fun bind(theater: Theater, context: Context) {
            if (theater.photo != "") {
                val resourceId = context.resources.getIdentifier(theater.photo, "drawable", context.packageName)
                photo?.setImageResource(resourceId)
            } else {
                photo?.setImageResource(R.mipmap.ic_launcher)
            }

            name?.text = theater.kind+ " "+ theater.name
            distance?.text = theater.distance + "km"

            itemView.setOnClickListener {
                theaterClick(theater)
            }
        }
    }
}
