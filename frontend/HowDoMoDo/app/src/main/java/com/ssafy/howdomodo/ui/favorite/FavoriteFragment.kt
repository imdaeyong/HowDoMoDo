package com.ssafy.howdomodo.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.data.datasource.model.Theater
import com.ssafy.howdomodo.ui.Favorites.FavoritesAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private val favoritesViewModel: FavoritesViewModel by viewModel()

    companion object {
        var favorite_select = false
        lateinit var selectedfavorite: Theater
        var userCode = 658903366
        lateinit var favoritesList: ArrayList<Theater>

    }

    var emptyList = arrayListOf<Theater>()

    lateinit var favoritesAdapter: FavoritesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        favoritesViewModel.favoritesInfo(UserCollection.userCode.toInt())

        favoritesViewModel.favoritesInfo(userCode)

        observeData()
    }


    fun observeData() {
        favoritesViewModel.favoritesError.observe(this, Observer {
            Log.e("즐겨찾기", "favorites observe 오류")
        })
        favoritesViewModel.favoritesResponse.observe(this, Observer {
            //영화 리스트 가져왔을떄.

            Log.e("즐겨찾기", "observe접속")
            if (it.status == 200) {
                Log.e("즐겨찾기", "통신 성공적")

                if (it.data != null) {
                    favoritesList = it.data!!
                } else {
                    favoritesList = emptyList
                }

                if (favoritesList == emptyList) {
                    frag_fav_tv_warning.visibility = View.VISIBLE
                }
                favoritesAdapter = FavoritesAdapter(
                    object :
                        FavoritesAdapter.FavoritesViewHolder.FavoritesClickListener {
                        override fun onclick(position: Int, textView: TextView) {
                            if (!favoritesAdapter.getClicked(position)) {
                                if (favoritesAdapter.getClickedFavorites() != -1) {
                                    favoritesAdapter.setClicked(
                                        favoritesAdapter.getClickedFavorites(),
                                        false
                                    )
                                }
                                favoritesAdapter.setClicked(position, true)
                                favorite_select = true
                                selectedfavorite = favoritesList[position]

                            } else if (favoritesAdapter.getClicked(position)) {
                                favoritesAdapter.setClicked(
                                    favoritesAdapter.getClickedFavorites(),
                                    false
                                )
                                favorite_select = false
                            }
                            Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT)
                        }

                        override fun starClick(position: Int, starImageView: ImageView) {
                            Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT)
                            Log.e("즐겨찾기 삭제", "starclick접속")
                            isStarClicked(position, starImageView)
                        }
                    })

                favoritesList = it.data!!
                favoritesAdapter.setFavoritesData(favoritesList)
                frag_fav_rv_favorite.adapter = favoritesAdapter
                var favoritelm = LinearLayoutManager(this.context)
                frag_fav_rv_favorite.layoutManager = favoritelm
                frag_fav_rv_favorite.setHasFixedSize(true)
            }
        }
        )
    }

    fun isStarClicked(position: Int, starImageView: ImageView) {
        var f_t = favoritesList[position]
        starImageView.setImageResource(R.drawable.star_unclicked)
        favoritesViewModel.favoritesDelete(userCode, f_t.theaterId)
        favoritesList.removeAt(position)

//        favoritesViewModel.favoritesInfo(userCode)
    }
}


