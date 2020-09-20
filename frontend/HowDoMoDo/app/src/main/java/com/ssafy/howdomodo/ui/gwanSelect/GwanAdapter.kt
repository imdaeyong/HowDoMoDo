package com.ssafy.howdomodo.ui.gwanSelect

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Gwan
import kotlinx.android.synthetic.main.item_gwan.view.*

class GwanAdapter : RecyclerView.Adapter<GwanViewHolder>() {
    companion object {
        var gwanData = ArrayList<Gwan>()
    }

    private lateinit var listener: GwanViewHolder.ClickListener

    fun setGwanListener(listener: GwanViewHolder.ClickListener) {
        this.listener = listener
    }

    fun setGwanData(newData: List<Gwan>) {
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
    fun bind(data: Gwan, position: Int) {
        movieTimeAdapter = MovieTimeAdapter(position, object : MovieTimeViewHolder.TimeClickListener {
            override fun timeClick(position: Int, parentPosition: Int) {
                if (!movieTimeAdapter.getClicked(parentPosition, position)) {
                    val clicked = movieTimeAdapter.getClickedMovieTime()
                    if (clicked != null) {
                        movieTimeAdapter.setClicked(clicked[0], clicked[1], false)
                    }
                    movieTimeAdapter.setClicked(parentPosition, position, true)
                    listener.onClick()
                }
            }
        })
        movieTimeAdapter.setTimeData(data.times)
        itemView.item_gwan_rv_seat.adapter = movieTimeAdapter
        itemView.item_gwan_rv_seat.layoutManager = GridLayoutManager(itemView.context, 5)
        itemView.item_gwan_rv_seat.setHasFixedSize(true)

        itemView.item_gwan_tv_title.text = data.kind
        itemView.item_gwan_tv_number.text = "${data.number}관"
        itemView.item_gwan_tv_seat_count.text = "${data.seatCount}석"

    }


}