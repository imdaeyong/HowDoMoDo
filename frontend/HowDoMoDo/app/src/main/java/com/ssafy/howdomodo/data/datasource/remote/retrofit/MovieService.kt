import com.ssafy.howdomodo.data.datasource.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/3/movie/now_playing")
    fun getMovie(
        @Query("api_key") key: String,
        @Query("language") keyword: String,
        ): Call<MovieResponse>

    @GET("/pyapi/current")
    fun getNewMovie(
    ): Call<MovieResponse>
}