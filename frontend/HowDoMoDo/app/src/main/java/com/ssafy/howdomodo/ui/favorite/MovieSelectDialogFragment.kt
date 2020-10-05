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
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.`object`.ObjectMovie
import com.ssafy.howdomodo.ui.main.MainAdapter
import com.ssafy.howdomodo.ui.main.MainViewHolder
import com.ssafy.howdomodo.ui.main.MovieViewModel
import com.ssafy.howdomodo.ui.selectArea.SelectAreaActivity
import kotlinx.android.synthetic.main.dialog_movie_select.view.*
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
        mvm.getNewMoviedata()
        mainAdapter = MainAdapter(object : MainViewHolder.ClickListener {
            override fun movieClick(position: Int) {
                val movieTitle = mainAdapter.movieData[position].title

                // movieTitle을 Request 보내서 긍,부정 데이터를 받아온다.
                val builder = AlertDialog.Builder(this@MovieSelectDialogFragment.context!!)
                val dialogView = layoutInflater.inflate(R.layout.item_ticketing_dialog, null)

                builder.setView(dialogView)
                    .setPositiveButton("예매") { dialogInterface, i ->
//                        item_ticketing_dialog_positive_score.text =
//                        item_ticketing_dialog_negative_score.text =
                        val intent = Intent(activity, SelectAreaActivity::class.java)
                        ObjectMovie.movieTitle = movieTitle
                        dismiss()
                        startActivity(intent)
                    }
                    .setNegativeButton("취소") { dialogInterface, i ->
                        /* 취소일 때 아무 액션이 없으므로 빈칸 */
                    }
                    .show()
            }
        })

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
}