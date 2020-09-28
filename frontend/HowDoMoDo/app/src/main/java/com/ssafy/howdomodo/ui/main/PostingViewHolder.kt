package com.ssafy.howdomodo.ui.main
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.howdomodo.data.datasource.model.Posting
import kotlinx.android.synthetic.main.item_main_posting.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PostingViewHolder (v: View,private val itemClickListener: ItemClickListener) : RecyclerView.ViewHolder(v) {
    var view : View = v

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    init {
        view.ll_main_btn_posting.setOnClickListener {
            itemClickListener.onItemClick(adapterPosition)
        }
    }

    fun bind(item: Posting){
        val title = item.title
        val re = "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>".toRegex()
        val re2 = "&lt;".toRegex()
        val re3 = "&gt;".toRegex()
        var changeTitle = title.replace(re, "")
        changeTitle = changeTitle.replace(re2, "")
        changeTitle = changeTitle.replace(re3, "")
        var changeContent = item.description.replace(re, "")
        changeContent = changeContent.replace(re2, "")
        changeContent = changeContent.replace(re3, "")

        view.ll_main_tv_posting_title.text = changeTitle
        view.ll_main_tv_posting_content.text = changeContent
        var string = item.postdate
        var year = string.subSequence(0,4)
        var month = string.subSequence(4,6)
        var day = string.subSequence(6,8)
        var result = year.toString() + "-"+month.toString()+"-"+day.toString()
        view.ll_main_tv_posting_date.text = result

    }
}