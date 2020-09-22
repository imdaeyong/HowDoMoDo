import com.ssafy.howdomodo.data.datasource.model.NaverApi

interface NaverRemoteDataSource {
    fun getBlogData(title: String, onResponse: (NaverApi) -> Unit, onFailure: (Throwable) -> Unit)
}