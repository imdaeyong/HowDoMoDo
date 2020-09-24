package com.ssafy.howdomodo.ui.main
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Movie

class MainAdapter (private val itemList : List<Movie>) : RecyclerView.Adapter<MainViewHolder>()  {

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_main_movie, parent, false)
        return MainViewHolder(inflatedView);
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        Log.e("Asd",position.toString())

        val item = itemList[position]
        holder.apply {
            Log.e("Asd","ASd")
            bind(item)
        }
    }

}