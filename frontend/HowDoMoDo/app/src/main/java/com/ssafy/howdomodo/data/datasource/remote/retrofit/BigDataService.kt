import com.ssafy.howdomodo.data.datasource.model.BigDataPsNs
import com.ssafy.howdomodo.data.datasource.model.MovieApi
import com.ssafy.howdomodo.data.datasource.model.SignUpResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BigDataService {

//    @GET("/bigdata/pn/{title}")
    @GET("/pyapi/suns/{title}")
    fun getMoviePsNs(
        @Path("title") movieTitle: String
        ): Call<BigDataPsNs>

}