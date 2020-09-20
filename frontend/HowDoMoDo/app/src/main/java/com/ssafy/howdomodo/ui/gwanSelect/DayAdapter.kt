package com.ssafy.howdomodo.ui.gwanSelect

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.MovieDate
import kotlinx.android.synthetic.main.item_day.view.*
import java.util.*
import kotlin.collections.ArrayList

class DayAdapter(private val onclick: DayViewHolder.DayClickListener) : RecyclerView.Adapter<DayViewHolder>() {
     private val dayData = ArrayList<MovieDate>()

    fun setDayData(data: List<MovieDate>) {
        with(dayData) {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    fun getClicked(position: Int): Boolean {
        return dayData[position].isClicked
    }

    fun getClickedDay(): Int {
        for (i in dayData.indices) {
            if (dayData[i].isClicked) {
                return i
            }
        }
        return -1
    }

    fun setClicked(position: Int, status: Boolean) {
        dayData[position].isClicked = status
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
        return DayViewHolder(inflatedView, onclick)
    }

    override fun getItemCount(): Int {
        return dayData.size
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(dayData[position])
    }
}

class DayViewHolder(itemView: View, private val dayClickListener: DayClickListener) : RecyclerView.ViewHolder(itemView) {

    interface DayClickListener {
        fun onclick(position: Int, textView: TextView)
    }

    init {
        itemView.item_day_cl_background.setOnClickListener {
            dayClickListener.onclick(adapterPosition, itemView.item_day_tv_text)
        }
    }

    fun bind(data: MovieDate) {
        val time = data.dayDate.time
        val cal = Calendar.getInstance()
        cal.timeInMillis = time
        val week = cal.get(Calendar.DAY_OF_WEEK)
        itemView.item_day_tv_text.text = Week.str[week - 1]
        val day = cal.get(Calendar.DAY_OF_MONTH)
        itemView.item_day_tv_number.text = day.toString()

        if (data.isClicked) {
            itemView.item_day_tv_text.setTextColor(Color.parseColor("#f73859"))
            itemView.item_day_tv_number.setTextColor(Color.parseColor("#f73859"))
        } else {
            itemView.item_day_tv_text.setTextColor(Color.WHITE)
            itemView.item_day_tv_number.setTextColor(Color.WHITE)
        }
    }
}

object Week {
    val str = listOf<String>("일", "월", "화", "수", "목", "금", "토")

    val times = listOf(MovieDate(Date(), true),
            MovieDate(Date(Date().time + (1000 * 60 * 60 * 24).toLong()), false),
            MovieDate(Date(Date().time + 2 * (1000 * 60 * 60 * 24).toLong()), false),
            MovieDate(Date(Date().time + 3 * (1000 * 60 * 60 * 24).toLong()), false),
            MovieDate(Date(Date().time + 4 * (1000 * 60 * 60 * 24).toLong()), false),
            MovieDate(Date(Date().time + 5 * (1000 * 60 * 60 * 24).toLong()), false),
            MovieDate(Date(Date().time + 6 * (1000 * 60 * 60 * 24).toLong()), false))

}
