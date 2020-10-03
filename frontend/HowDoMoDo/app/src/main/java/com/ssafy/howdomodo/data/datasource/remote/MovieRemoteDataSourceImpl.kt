package com.ssafy.howdomodo.data.datasource.remote
import NaverSevicelmpl
import com.ssafy.howdomodo.data.datasource.model.BigDataPsNs
import com.ssafy.howdomodo.data.datasource.model.MovieApi
import com.ssafy.howdomodo.data.datasource.model.NaverApi
import retrofit2.Call
import retrofit2.Response

class MovieRemoteDataSourceImpl : MovieRemoteDataSource {
    override fun getMovieData(
        key: String,
        region: String,
        onResponse: (MovieApi) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MovieSevicelmpl.service.getMovie(key,region).enqueue(
            object : retrofit2.Callback<MovieApi> {
                override fun onFailure(call: Call<MovieApi>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(call: Call<MovieApi>, response: Response<MovieApi>) {
                    onResponse(response.body()!!)
                }
            }
        )
    }

    override fun getMoviePsNs(
        title: String,
        onResponse: (BigDataPsNs) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        BigDataSevicelmpl.service.getMoviePsNs(title).enqueue(
            object : retrofit2.Callback<BigDataPsNs> {
                override fun onFailure(call: Call<BigDataPsNs>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(call: Call<BigDataPsNs>, response: Response<BigDataPsNs>) {
                    onResponse(response.body()!!)
                }
            }
        )
    }
}