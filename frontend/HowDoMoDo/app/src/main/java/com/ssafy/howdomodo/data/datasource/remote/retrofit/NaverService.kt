import com.ssafy.howdomodo.data.datasource.model.NaverApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverService {

    @GET("/v1/search/blog.json")
    fun getBlog(
        @Query("query") keyword: String,
        ): Call<NaverApi>

}