import com.ssafy.howdomodo.Intercepter.CookiesIntercepter
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BigDataSevicelmpl {
    private const val BASE_URL = "http://j3a305.p.ssafy.io:8000"

    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: BigDataService = retrofit.create(
        BigDataService::class.java
    )
}