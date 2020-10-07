package com.ssafy.howdomodo.data.datasource.remote
import BigDataSevicelmpl
import MovieSevicelmpl
import android.util.Log
import com.ssafy.howdomodo.data.datasource.model.BigDataPsNs
import com.ssafy.howdomodo.data.datasource.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieRemoteDataSourceImpl : MovieRemoteDataSource {
    override fun getMovieData(
        key: String,
        region: String,
        onResponse: (MovieResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MovieSevicelmpl.service.getMovie(key,region).enqueue(
            object : retrofit2.Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    onResponse(response.body()!!)
                }
            }
        )
    }

    override fun getMoviePsNs(
        code: String,
        onResponse: (BigDataPsNs) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
//        Log.e("TT1",code)
        BigDataSevicelmpl.service.getMoviePsNs(code).enqueue(
            object : retrofit2.Callback<BigDataPsNs> {
                override fun onFailure(call: Call<BigDataPsNs>, t: Throwable) {
//                    Log.e("TT2",t.toString())
                    onFailure(t)
                }

                override fun onResponse(call: Call<BigDataPsNs>, response: Response<BigDataPsNs>) {
                    //Log.e("TT3",response.message())
                    onResponse(response.body()!!)
                }
            }
        )
    }

    override fun getNewMovieData(success: (MovieResponse) -> Unit, fail: (Throwable) -> Unit) {
        MovieSevicelmpl.service2.getNewMovie().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                success(response.body()!!)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                fail(t)
            }
        })
    }
}