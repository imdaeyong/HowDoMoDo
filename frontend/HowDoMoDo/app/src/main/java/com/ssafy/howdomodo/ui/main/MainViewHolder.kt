package com.ssafy.howdomodo.ui.main
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Movie
import kotlinx.android.synthetic.main.item_main_movie.view.*

class MainViewHolder(v: View, private val clickListener: ClickListener) :
    RecyclerView.ViewHolder(v) {

    interface ClickListener {
        fun movieClick(position: Int)
    }

    init {
        v.ll_main_btn_ticketing.setOnClickListener {
            clickListener.movieClick(adapterPosition)
        }
    }

    var view: View = v
    var genres = mutableMapOf(
        28 to "액션",
        12 to "모험",
        16 to "애니메이션",
        35 to "코미디",
        80 to "범죄",
        99 to "다큐멘터리",
        18 to "드라마",
        10751 to "가족",
        14 to "판타지",
        36 to "역사",
        27 to "공포",
        10402 to "음악",
        9648 to "미스테리",
        10749 to "로맨스",
        878 to "공상 과학",
        10770 to "TV 영화",
        53 to "스릴러",
        10752 to "전쟁",
        37 to "서양"
    )

    fun bind(item: Movie) {
//        var posterURL = "https://image.tmdb.org/t/p/w500/" + item.posterPath
        Glide.with(view.context).load(item.posterPath).into(view.ll_main_iv_poster)
        //Log.e("movieID", item.id.toString())
        view.ll_main_tv_name.text = item.title
//        var genre = ""
//        var count = 0
//        for(i in item.genreIds){
//            genre+=genres.get(i)
//            count++
//            if(count >1 || count == item.genreIds.size) break
//            genre +="/"
//        }
        when (item.age) {
            "12" -> view.ll_main_iv_age.setImageResource(R.drawable.age_twelve)
            "15" -> view.ll_main_iv_age.setImageResource(R.drawable.age_fifteen)
            "19" -> view.ll_main_iv_age.setImageResource(R.drawable.age_nineteen)
            "전체" -> view.ll_main_iv_age.setImageResource(R.drawable.age_all)
        }
        view.ll_main_tv_mvtype.text = item.genreIds
        view.ll_main_tv_mvstar.text = item.voteAverage.toString()

    }
}