package com.ssafy.howdomodo.ui.gwanSelect

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.MovieTime
import kotlinx.android.synthetic.main.item_movie_time.view.*

class MovieTimeAdapter(private val parentPosition: Int, private val timeClick: MovieTimeViewHolder.TimeClickListener) : RecyclerView.Adapter<MovieTimeViewHolder>() {
    private val timeData = ArrayList<MovieTime>()

    fun setTimeData(newData: List<MovieTime>) {
        with(timeData) {
            clear()
            addAll(newData)
        }
        notifyDataSetChanged()
    }

    fun getClickedMovieTime(): ArrayList<Int>? {
        val list = ArrayList<Int>()
        for (i in GwanAdapter.gwanData.indices) {
            for (j in GwanAdapter.gwanData[i].timeList.indices) {
                if (GwanAdapter.gwanData[i].timeList[j].isClicked) {
                    list.add(i)
                    list.add(j)
                    return list
                }
            }
        }
        return null
    }

    fun getClicked(parentPosition: Int, position: Int): Boolean {
        return GwanAdapter.gwanData[parentPosition].timeList[position].isClicked
    }

    fun setClicked(parentPosition: Int, position: Int, status: Boolean) {
        GwanAdapter.gwanData[parentPosition].timeList[position].isClicked = status
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTimeViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_time, parent, false)
        return MovieTimeViewHolder(inflatedView, timeClick, parentPosition)
    }

    override fun getItemCount(): Int {
        return timeData.size
    }

    override fun onBindViewHolder(holder: MovieTimeViewHolder, position: Int) {
        holder.bind(timeData[position])
    }
}

class MovieTimeViewHolder(itemView: View, private val timeClickListener: TimeClickListener, private val parentPosition: Int) : RecyclerView.ViewHolder(itemView) {

    interface TimeClickListener {
        fun timeClick(position: Int, parentPosition: Int)
    }

    init {
        itemView.item_movie_time_cl_box.setOnClickListener {
            timeClickListener.timeClick(adapterPosition, parentPosition)
        }
    }

    fun bind(data: MovieTime) {
        itemView.item_movie_time_tv_start_time.text = data.time
        itemView.item_movie_time_tv_end_time.text = data.title
        itemView.item_movie_time_tv_percent.text = "${data.count}석"

        if (data.isClicked) {
            itemView.item_movie_time_cl_box.setBackgroundResource(R.drawable.movie_time_selected)
        } else {
            itemView.item_movie_time_cl_box.setBackgroundResource(R.drawable.movie_time_un_selected)
        }
    }
}