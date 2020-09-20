package com.ssafy.howdomodo.ui.main
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Movie
import com.ssafy.howdomodo.data.datasource.model.Posting

class PostingAdapter (private val itemList : List<Posting>) : RecyclerView.Adapter<PostingViewHolder>()  {

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostingViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.activity_main_posting, parent, false)
        return PostingViewHolder(inflatedView);
    }

    override fun onBindViewHolder(holder: PostingViewHolder, position: Int) {
        Log.e("Asd",position.toString())

        val item = itemList[position]
        holder.apply {
            Log.e("Asd","ASd")
            bind(item)
        }
    }

}