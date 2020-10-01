package com.ssafy.howdomodo.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Theater

class FavoriteAdapter : RecyclerView.Adapter<FavoriteViewHolder>() {

    private val favoriteData = ArrayList<Theater>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return FavoriteViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return favoriteData.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        
    }
}

class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}