import com.ssafy.howdomodo.data.datasource.model.BigDataPsNs
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BigDataService {

//    @GET("/bigdata/pn/{title}")
    @GET("/pyapi/np/{code}")
    fun getMoviePsNs(
        @Path("code") movieCode: String
        ): Call<BigDataPsNs>

}