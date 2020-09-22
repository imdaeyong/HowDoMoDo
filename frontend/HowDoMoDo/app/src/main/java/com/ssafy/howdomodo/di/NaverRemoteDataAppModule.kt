import com.ssafy.howdomodo.data.datasource.remote.NaverRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataAppModule =
    module { single<NaverRemoteDataSource> { NaverRemoteDataSourceImpl() } }