package com.ssafy.howdomodo.ui.favorite

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.`object`.ObjectMovie
import com.ssafy.howdomodo.`object`.TheaterCollection
import com.ssafy.howdomodo.ui.Loading
import com.ssafy.howdomodo.ui.gwanSelect.GwanSelectActivity
import com.ssafy.howdomodo.ui.main.*
import com.ssafy.howdomodo.ui.selectArea.SelectAreaActivity
import kotlinx.android.synthetic.main.dialog_movie_select.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_ticketing_dialog.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieSelectDialogFragment : DialogFragment() {
    private val mvm: MovieViewModel by viewModel()

    lateinit var mainAdapter: MainAdapter
    fun getInstance(): MovieSelectDialogFragment {
        return MovieSelectDialogFragment()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(activity!!, theme) {
            override fun onBackPressed() {
                dismiss()
            }
        }
    }

    private fun setBackground() {
        // 배경에 희게 각지게 나오는 거 방지.
        with(dialog!!.window!!) {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflatedView = inflater.inflate(R.layout.dialog_movie_select, container, false)
        setBackground()
        Loading.goLoading(activity!!)
        mvm.getNewMoviedata()
        mainAdapter = MainAdapter(object : MainViewHolder.ClickListener {
            override fun movieClick(position: Int) {
                val movieCode = mainAdapter.movieData[position].id
                TheaterCollection.mvPoster = mainAdapter.movieData[position].posterPath
                TheaterCollection.mvTitle = mainAdapter.movieData[position].title

                MainFragment.moviePosition = position
                Loading.goLoading(activity!!)
                mvm.getMoviePsNs(movieCode)
//
//                val movieTitle = mainAdapter.movieData[position].title
//
//                // movieTitle을 Request 보내서 긍,부정 데이터를 받아온다.
//                val builder = AlertDialog.Builder(this@MovieSelectDialogFragment.context!!)
//                val dialogView = layoutInflater.inflate(R.layout.item_ticketing_dialog, null)
//
//                builder.setView(dialogView)
//                    .setPositiveButton("예매") { dialogInterface, i ->
////                        item_ticketing_dialog_positive_score.text =
////                        item_ticketing_dialog_negative_score.text =
//                        val intent = Intent(activity, GwanSelectActivity::class.java)
//                        TheaterCollection.mvPoster = mainAdapter.movieData[position].posterPath
//                        TheaterCollection.mvTitle = mainAdapter.movieData[position].title
//                        dismiss()
//                        startActivity(intent)
//                    }
//                    .setNegativeButton("취소") { dialogInterface, i ->
//                        /* 취소일 때 아무 액션이 없으므로 빈칸 */
//                    }
//                    .show()
            }

        })

        movieObserve(mainAdapter)

        mvm.movieData.observe(this, Observer {
            val movieList = it
            Log.d("TEST", movieList.size.toString())
            mainAdapter.setMovieItemList(movieList)
        })

        inflatedView.dialog_select_movie_rv.adapter = mainAdapter
        inflatedView.dialog_select_movie_rv.layoutManager = LinearLayoutManager(this.context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL
        }

        inflatedView.dialog_select_movie_rv.setHasFixedSize(true)

        return inflatedView
    }

    fun movieObserve(mainAdapter: MainAdapter) {

        mvm.psNsLoading.observe(this, Observer {
            Loading.exitLoading()
        })
        mvm.loading.observe(this, Observer {
            MainFragment.movieDataBool = it

            if (!MainFragment.movieDataBool && !MainFragment.postDataBool) {
                Loading.exitLoading()
            }
        })
        
        mvm.movieData.observe(this, Observer {
            val movieList = it
            mainAdapter.setMovieItemList(movieList)
        })
        mvm.psnsData.observe(this, Observer {
            TheaterCollection.mvTitle = mainAdapter.movieData[MainFragment.moviePosition].title
            TheaterCollection.mvPoster = mainAdapter.movieData[MainFragment.moviePosition].posterPath
            TheaterCollection.age = mainAdapter.movieData[MainFragment.moviePosition].age

            // movieTitle을 Request 보내서 긍,부정 데이터를 받아온다.
            val movieTitle = mainAdapter.movieData[MainFragment.moviePosition].title
            val dialogView = layoutInflater.inflate(R.layout.item_ticketing_dialog, null)
            var PsNs = "긍정 점수:" + it.ps + "% 부정 점수:" + it.ns + "%"

            val dialog = PsnsDialog(view!!.context)
//            item_psns_content.text = PsNs
            dialog.setOnOKClickedListener { content->
                if(content == "확인"){
                    val intent = Intent(activity, SelectAreaActivity::class.java)
                    ObjectMovie.movieTitle = movieTitle
                    startActivity(intent)
                    dismiss()
                }else{
                    Toast.makeText(view!!.context, "취소", Toast.LENGTH_SHORT).show()
                }
            }
            dialog.start(PsNs)
        })
    }
}