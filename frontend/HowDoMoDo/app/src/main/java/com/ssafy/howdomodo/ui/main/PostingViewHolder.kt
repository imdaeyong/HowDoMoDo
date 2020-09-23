package com.ssafy.howdomodo.ui.main
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.data.datasource.model.Posting
import kotlinx.android.synthetic.main.item_main_posting.view.*

class PostingViewHolder (v: View) : RecyclerView.ViewHolder(v) {
    var view : View = v

    interface ItemClickListener {
        fun onItemClick(position: Int,textView: TextView)
    }

    fun bind(item: Posting){
        Log.e("item", item.title)

        view.ll_main_tv_posting_title.text = item.title
        view.ll_main_tv_posting_content.text = item.description
//        view.ll_main_tv_posting_date.text = item.postdate.toString()

    }
}