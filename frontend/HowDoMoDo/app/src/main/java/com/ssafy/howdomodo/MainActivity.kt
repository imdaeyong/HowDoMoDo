package com.ssafy.howdomodo;

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.howdomodo.data.datasource.model.Movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var movieList : List<Movie> = listOf(
            Movie("공작","액션",3.4,"url/image1"),
            Movie("미스터빈","코미디",2.1,"url/image2"),
            Movie("암살","액션",4.9,"url/image3"),
            Movie("괴물","스릴러",4.6,"url/image4")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = MainAdapter(this.movieList)
        act_main_rv_movie.adapter = adapter
        act_main_rv_movie.layoutManager = LinearLayoutManager(this).also { it.orientation = LinearLayoutManager.HORIZONTAL }
        act_main_rv_movie.setHasFixedSize(true)

//        val sf = getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
//        val token = sf.getString("token","")
//        if(token.equals("")){
//
//        }else{
//            // 통신코드
//        }

    }

}