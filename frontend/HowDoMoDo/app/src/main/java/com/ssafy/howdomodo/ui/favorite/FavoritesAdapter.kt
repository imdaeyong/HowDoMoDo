package com.ssafy.howdomodo.ui.Favorites

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Theater
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoritesAdapter(private val onclick: FavoritesViewHolder.FavoritesClickListener) :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    val favoritesData = ArrayList<Theater>()


    fun setFavoritesData(newData: List<Theater>) {
        with(favoritesData) {
            clear()
            addAll(newData)
        }
        notifyDataSetChanged()
    }

    fun getClicked(position: Int): Boolean {
        return favoritesData[position].isClicked
    }

    fun getClickedFavorites(): Int {
        for (i in favoritesData.indices) {
            if (favoritesData[i].isClicked) {
                return i
            }
        }
        return -1
    }

    fun setClicked(position: Int, status: Boolean) {
        favoritesData[position].isClicked = status

        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return FavoritesViewHolder(
            view,
            onclick
        )
    }

    override fun getItemCount(): Int {
        return favoritesData.size
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(favoritesData[position],)
    }

    class FavoritesViewHolder(
        itemView: View,
        private val favoritesClickListener: FavoritesClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        interface FavoritesClickListener {
            fun onclick(position: Int, textView: TextView)
            fun starClick(position: Int, starImageView: ImageView)
        }

        init {
            itemView.item_favorite_cl_box.setOnClickListener {
                favoritesClickListener.onclick(
                    adapterPosition,
                    itemView.item_favorite_theater_tv_name
                )
            }
            itemView.item_favorite_theater_iv_photo.setOnClickListener {
                favoritesClickListener.starClick(
                    adapterPosition,
                    itemView.item_favorite_theater_iv_star
                )
            }
        }


        fun bind(data: Theater) {
            var name = data.theaterBrand + " " + data.theaterName
            var address = data.theaterAddress
            var theater_lat = data.theaterLat
            var theater_lng = data.theaterLon

            itemView.item_favorite_theater_tv_name.text = name
            itemView.item_favorite_theater_tv_desc.text = address

            if (data.theaterBrand.contains("CGV")) {
                itemView.item_favorite_theater_iv_photo.setImageResource(R.drawable.cgv)
            } else if (data.theaterBrand.contains("메가박스")) {
                itemView.item_favorite_theater_iv_photo.setImageResource(R.drawable.megabox)
            } else if (data.theaterBrand.contains("롯데시네마")) {
                itemView.item_favorite_theater_iv_photo.setImageResource(R.drawable.lottecinema)
            }

            if (data.isClicked) {
                itemView.item_favorite_cl_box.setBackgroundColor(Color.parseColor("#EEEEEE"))
                itemView.item_favorite_theater_tv_name.setTextColor(Color.parseColor("#f73859"))
            } else {
                itemView.item_favorite_cl_box.setBackgroundColor(Color.parseColor("#00000000"))
                itemView.item_favorite_theater_tv_name.setTextColor(Color.parseColor("#ffffff"))
            }

        }
    }
}
