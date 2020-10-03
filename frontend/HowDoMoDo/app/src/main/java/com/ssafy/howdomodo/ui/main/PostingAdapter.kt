package com.ssafy.howdomodo.ui.main
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Posting

class PostingAdapter (private val clickListener: PostingViewHolder.ItemClickListener) : RecyclerView.Adapter<PostingViewHolder>()  {

     val blogData: ArrayList<Posting> = ArrayList()

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
        return PostingViewHolder(inflatedView,clickListener)
    }


    override fun onBindViewHolder(holder: PostingViewHolder, position: Int) {

        val item = blogData[position]
        holder.apply {
            bind(item)
        }
    }

}