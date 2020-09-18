package com.ssafy.howdomodo
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.data.datasource.model.Movie
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main_movie.view.*

class MainViewHolder (v: View) : RecyclerView.ViewHolder(v) {
    var view : View = v

    fun bind(item: Movie){
        Log.e("item", item.movieName)

        view.ll_main_tv_name.text = item.movieName
//        view.ll_main_iv_poster. = item.moviePoster
        view.ll_main_tv_type.text = item.movieType
        view.ll_main_tv_star.text = item.movieStar.toString()

    }
}