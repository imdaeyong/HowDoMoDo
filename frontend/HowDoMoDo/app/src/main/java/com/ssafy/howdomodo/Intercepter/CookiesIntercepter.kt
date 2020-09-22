package com.ssafy.howdomodo.Intercepter

import com.ssafy.howdomodo.`object`.ObjectCollection
import okhttp3.Interceptor
import okhttp3.Response

class CookiesIntercepter : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request =
            chain.request().newBuilder().header("X-Naver-Client-Id", ObjectCollection.API_ID)
                .header("X-Naver-Client-Secret", ObjectCollection.API_SECRET)
                .build()
        return chain.proceed(request)
    }
}