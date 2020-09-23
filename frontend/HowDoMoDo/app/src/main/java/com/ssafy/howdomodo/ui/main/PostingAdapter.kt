package com.ssafy.howdomodo.ui.main
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Posting

class PostingAdapter () : RecyclerView.Adapter<PostingViewHolder>()  {

    private val blogData: ArrayList<Posting> = ArrayList()

    fun setBlogItemList(newBlogData: List<Posting>) {
        with(blogData) {
            clear()
            addAll(newBlogData)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return blogData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostingViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_main_posting, parent, false)
        return PostingViewHolder(inflatedView);
    }

    override fun onBindViewHolder(holder: PostingViewHolder, position: Int) {
        Log.e("Asd",position.toString())

        val item = blogData[position]
        holder.apply {
            Log.e("Asd","ASd")
            bind(item)
        }
    }

}