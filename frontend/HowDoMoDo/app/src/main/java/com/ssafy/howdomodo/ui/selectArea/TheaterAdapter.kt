package com.ssafy.howdomodo.ui.selectArea

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Theater
import kotlinx.android.synthetic.main.item_theater.view.*

class TheaterAdapter(private val onclick: TheaterViewHolder.TheaterClickListener) :
    RecyclerView.Adapter<TheaterAdapter.TheaterViewHolder>() {

    private val theaterData = ArrayList<Theater>()
    parent.conte


    fun setTheaterData(newData: List<Theater>) {
        with(theaterData) {
            clear()
            addAll(newData)
        }
        notifyDataSetChanged()
    }

    fun getClicked(position: Int): Boolean {
        return theaterData[position].isClicked
    }

    fun getClickedTheater(): Int {
        for (i in theaterData.indices) {
            if (theaterData[i].isClicked) {
                return i
            }
        }
        return -1
    }

    fun setClicked(position: Int, status: Boolean) {
        theaterData[position].isClicked = status

        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheaterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_theater, parent, false)
        return TheaterViewHolder(view, onclick)
    }

    override fun getItemCount(): Int {
        return theaterData.size
    }

    override fun onBindViewHolder(holder: TheaterViewHolder, position: Int) {
        holder.bind(theaterData[position],)
    }

    class TheaterViewHolder(
        itemView: View,
        private val theaterClickListener: TheaterClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        interface TheaterClickListener {
            fun onclick(position: Int, textView: TextView)
        }

        init {
            itemView.item_theater_cl_box.setOnClickListener {
                theaterClickListener.onclick(
                    adapterPosition,
                    itemView.item_select_area_theater_tv_name
                )
            }
        }


        fun bind(data: Theater) {
            var distance = data.distance + "km"
            var name = data.kind + " " + data.name
            var photo = data.photo
            var photo_iv = itemView.item_select_area_theater_iv_photo

            itemView.item_select_area_theater_tv_name.text = name
            itemView.item_select_area_theater_tv_distance.text = distance




            if (data.photo != "") {
                val resourceId =
                    context.resources.getIdentifier(data.photo, "drawable", context.packageName)
                photo_iv.setImageResource(resourceId)
            } else {
                photo_iv.setImageResource(R.mipmap.ic_launcher)
            }

            if (data.isClicked) {
                itemView.item_theater_cl_box.setBackgroundColor(Color.parseColor("#EEEEEE"))
            } else {
                itemView.item_theater_cl_box.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }

        }
    }
}
