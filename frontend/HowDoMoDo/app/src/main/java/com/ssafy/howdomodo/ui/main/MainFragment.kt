package com.ssafy.howdomodo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Movie
import com.ssafy.howdomodo.data.datasource.model.Posting
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    var movieList : List<Movie> = listOf(
            Movie("공작","액션",3.4,"url/image1"),
            Movie("미스터빈","코미디",2.1,"url/image2"),
            Movie("암살","액션",4.9,"url/image3"),
            Movie("괴물","스릴러",4.6,"url/image4")
    )
    var postList : List<Posting> = listOf(
            Posting("공작","공작 첩보물 최고 꿀잼 영화 등판!","사진은 권력이다. 영화 공작, 남과 북의 공작 정치에 대한 고발장이자 반성문 같은 영화...","2016년 12월 18일","www.naver.com")
            ,Posting("공작","공작 첩보물 최고 꿀잼 영화 등판!","사진은 권력이다. 영화 공작, 남과 북의 공작 정치에 대한 고발장이자 반성문 같은 영화...","2016년 12월 18일","www.naver.com")
            ,Posting("공작","공작 첩보물 최고 꿀잼 영화 등판!","사진은 권력이다. 영화 공작, 남과 북의 공작 정치에 대한 고발장이자 반성문 같은 영화...","2016년 12월 18일","www.naver.com")
            ,Posting("공작","공작 첩보물 최고 꿀잼 영화 등판!","사진은 권력이다. 영화 공작, 남과 북의 공작 정치에 대한 고발장이자 반성문 같은 영화...","2016년 12월 18일","www.naver.com")
    )


    var postingList = arrayOf("테넷↓","인턴↓","공작↓")
    var univIdx = -1

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MainAdapter(this.movieList)
        act_main_rv_movie.adapter = adapter
        act_main_rv_movie.layoutManager = LinearLayoutManager(this.context).also { it.orientation = LinearLayoutManager.HORIZONTAL }
        act_main_rv_movie.setHasFixedSize(true)

        val postIngAdapter = PostingAdapter(this.postList);
        act_main_rv_posting.adapter = postIngAdapter
        act_main_rv_posting.layoutManager = LinearLayoutManager(this.context).also { it.orientation = LinearLayoutManager.HORIZONTAL }
        act_main_rv_posting.setHasFixedSize(true)

        act_main_spinner_posting.adapter = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_dropdown_item, postingList)

        //아이템 선택 리스너
        act_main_spinner_posting.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                println("영화를 선택하세요")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                println("영화 제목: " +postingList[position])
                // 선텍한 영화의 Posting 목록을 보여주기
//              summary.text = postingList[position];

                /*
                val button = Button(applicationContext)
                button.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
                button.text = "상세보기"
                button.setOnClickListener {
                    println("상세보기 액티비티 열기")
                }
                constraintLayout.addView(button)
                 */
            }

        }
    }
}