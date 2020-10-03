package com.ssafy.howdomodo.ui.favorite

//import com.ssafy.howdomodo.ui.theater.favoritesViewModel
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
import com.ssafy.howdomodo.`object`.UserCollection
import com.ssafy.howdomodo.data.datasource.model.Theater
import com.ssafy.howdomodo.ui.Favorites.FavoritesAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private val favoritesViewModel: FavoritesViewModel by viewModel()

    companion object {
        var theater_select = false
        lateinit var selectedTheater: Theater
        var userCode = "825425162"
        lateinit var favoritesAdapter: FavoritesAdapter

    }
    var favoritesList = arrayListOf<Theater>(
        Theater(1246, 175, "브로드웨이(신사)", "서울특별시 강남구 논현동 도산대로 8길 8", "롯데시네마", 37.5164, 127.022),
        Theater(1247, 175, "도곡", "서울특별시 강남구 도곡동 174-3", "롯데시네마", 37.4875, 127.047),
        Theater(1248, 175, "청남씨네시티", "서울특별시 강남구 도산대로 323, 씨네시티빌딩 14층", "CGV", 37.5229, 127.037),
    )

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
        favoritesViewModel.favoritesInfo(userCode.toInt())

        observeData()
    }


    private fun observeData() {
        favoritesViewModel.favoritesError.observe(this, Observer {
            Log.e("즐겨찾기", "favorites observe 오류")
        })
        favoritesViewModel.favoritesResponse.observe(this, Observer {
            //영화 리스트 가져왔을떄.
            if (it.status == 200) {
                Log.e("즐겨찾기", "통신 성공적")
                if(it.data!=null)
                {
                    favoritesList = it.data!!
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
                                theater_select = true
                                selectedTheater = favoritesList[position]
                            } else if (favoritesAdapter.getClicked(position)) {
//                                    ObjectMovie.movieTheater =  theaterAdapter.theaterData[position].theaterBrand + " " + theaterAdapter.theaterData[position].theaterName
                                favoritesAdapter.setClicked(
                                    favoritesAdapter.getClickedFavorites(),
                                    false
                                )
                                theater_select = false
                            }
//                                setButtonActive()
                        }

                        override fun starClick(position: Int, starImageView: ImageView) {
                            Toast.makeText(
                                view?.context,
                                position.toString(),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    })
                favoritesAdapter.setFavoritesData(favoritesList)
                frag_fav_rv_favorite.adapter = favoritesAdapter
                var theaterlm = LinearLayoutManager(this.context)
                frag_fav_rv_favorite.layoutManager = theaterlm
                frag_fav_rv_favorite.setHasFixedSize(true)


            }

        }
        )
    }
}


