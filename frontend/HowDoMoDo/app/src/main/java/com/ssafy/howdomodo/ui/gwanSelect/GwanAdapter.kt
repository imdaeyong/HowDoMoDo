package com.ssafy.howdomodo.ui.gwanSelect

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.`object`.TheaterCollection
import com.ssafy.howdomodo.data.datasource.model.TimeTable
import kotlinx.android.synthetic.main.item_gwan.view.*

class GwanAdapter : RecyclerView.Adapter<GwanViewHolder>() {
    companion object {
        var gwanData = ArrayList<TimeTable>()
    }

    private lateinit var listener: GwanViewHolder.ClickListener

    fun setGwanListener(listener: GwanViewHolder.ClickListener) {
        this.listener = listener
    }

    fun getClickedMovieTime(): ArrayList<Int>? {
        val list = ArrayList<Int>()
        Log.e("data:",GwanAdapter.gwanData.toString())
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

    fun setClicked(parentPosition: Int, position: Int, status: Boolean) {
        GwanAdapter.gwanData[parentPosition].timeList[position].isClicked = status
        notifyDataSetChanged()
    }

    fun setGwanData(newData: List<TimeTable>) {
        with(gwanData) {
            clear()
            addAll(newData)
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GwanViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_gwan, parent, false)
        return GwanViewHolder(inflatedView, listener)
    }

    override fun getItemCount(): Int {
        return gwanData.size
    }

    override fun onBindViewHolder(holder: GwanViewHolder, position: Int) {
        holder.bind(gwanData[position], position)
    }
}

class GwanViewHolder(itemView: View, private val listener: ClickListener) : RecyclerView.ViewHolder(itemView) {

    interface ClickListener {
        fun onClick()
    }

    lateinit var movieTimeAdapter: MovieTimeAdapter
    fun bind(data: TimeTable, position: Int) {
        movieTimeAdapter =
            MovieTimeAdapter(position, object : MovieTimeViewHolder.TimeClickListener {
                override fun timeClick(position: Int, parentPosition: Int) {
                    if (!movieTimeAdapter.getClicked(parentPosition, position)) {
                        val clicked = movieTimeAdapter.getClickedMovieTime()
                        if (clicked == null) {
                            movieTimeAdapter.setClicked(parentPosition, position, true)
                        } else {
                            movieTimeAdapter.setClicked(clicked[0], clicked[1], false)
                            movieTimeAdapter.setClicked(parentPosition, position, true)
                        }
                        listener.onClick()
                    } else {
                        movieTimeAdapter.setClicked(parentPosition, position, false)
                        listener.onClick()
                    }
                    TheaterCollection.mvType = GwanAdapter.gwanData[parentPosition].screen
                    TheaterCollection.mvTheaterNum = GwanAdapter.gwanData[parentPosition].hall
                    Log.e("screent",TheaterCollection.mvTheaterNum)
                }
            })
        movieTimeAdapter.setTimeData(data.timeList)
        itemView.item_gwan_rv_seat.adapter = movieTimeAdapter
        itemView.item_gwan_rv_seat.layoutManager = GridLayoutManager(itemView.context, 5)
        itemView.item_gwan_rv_seat.setHasFixedSize(true)

        itemView.item_gwan_tv_title.text = data.screen
        itemView.item_gwan_tv_number.text = data.hall
        itemView.item_gwan_tv_seat_count.text = "${data.total}석"

    }


}