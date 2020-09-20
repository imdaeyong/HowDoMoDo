package com.ssafy.howdomodo.ui.main
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.data.datasource.model.Movie
import com.ssafy.howdomodo.data.datasource.model.Posting
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main_movie.view.*
import kotlinx.android.synthetic.main.activity_main_posting.view.*

class PostingViewHolder (v: View) : RecyclerView.ViewHolder(v) {
    var view : View = v

    fun bind(item: Posting){
        Log.e("item", item.movieName)

        view.ll_main_tv_posting_title.text = item.movieTitle
        view.ll_main_tv_posting_content.text = item.movieContent
        view.ll_main_tv_posting_date.text = item.movieDate

    }
}