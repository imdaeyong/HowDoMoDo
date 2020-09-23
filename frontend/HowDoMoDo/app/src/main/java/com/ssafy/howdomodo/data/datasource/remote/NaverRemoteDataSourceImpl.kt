package com.ssafy.howdomodo.data.datasource.remote
import NaverSevicelmpl
import com.ssafy.howdomodo.data.datasource.model.NaverApi
import retrofit2.Call
import retrofit2.Response

class NaverRemoteDataSourceImpl : NaverRemoteDataSource {
    override fun getBlogData(
        title: String,
        onResponse: (NaverApi) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        NaverSevicelmpl.service.getBlog("공작").enqueue(
            object : retrofit2.Callback<NaverApi> {
                override fun onFailure(call: Call<NaverApi>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(call: Call<NaverApi>, response: Response<NaverApi>) {
                    onResponse(response.body()!!)
                }

            }
        )
    }
}