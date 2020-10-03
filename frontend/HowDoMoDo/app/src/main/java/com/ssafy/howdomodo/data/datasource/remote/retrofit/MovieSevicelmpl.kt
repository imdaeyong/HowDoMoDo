import com.ssafy.howdomodo.Intercepter.CookiesIntercepter
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieSevicelmpl {
    private const val BASE_URL = "https://api.themoviedb.org"

    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: MovieService = retrofit.create(
        MovieService::class.java
    )
}