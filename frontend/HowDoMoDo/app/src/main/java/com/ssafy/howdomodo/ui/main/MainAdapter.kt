package com.ssafy.howdomodo.ui.main
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Movie

class MainAdapter(private val clickListener: MainViewHolder.ClickListener) :
    RecyclerView.Adapter<MainViewHolder>() {

    val movieData: ArrayList<Movie> = ArrayList()

    fun setMovieItemList(newMovieData: List<Movie>) {
        with(movieData) {
            clear()
            addAll(newMovieData)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_main_movie, parent, false)
        return MainViewHolder(inflatedView, clickListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = movieData[position]
        holder.apply {
            Log.e("MainAdapter","MainAdapter")
            bind(item)
        }
    }
}