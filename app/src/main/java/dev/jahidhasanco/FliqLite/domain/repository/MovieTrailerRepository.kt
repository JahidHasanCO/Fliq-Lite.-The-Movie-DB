package dev.jahidhasanco.FliqLite.domain.repository



import dev.jahidhasanco.FliqLite.data.model.YoutubeTrailer.toDomainTrailer
import dev.jahidhasanco.FliqLite.data.remote.ApiService
import dev.jahidhasanco.FliqLite.domain.model.trailer.TrailerData
import dev.jahidhasanco.FliqLite.utils.Resource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieTrailerRepository
@Inject
constructor(
    private val apiService: ApiService
) {

     fun getTrailer(id: String): Flow<Resource<List<TrailerData>>> = flow{

        try {
            emit(Resource.Loading())

            val response = apiService.getMovieTrailer(id,"")

            val list = if(response.results.isNullOrEmpty()) emptyList<TrailerData>() else response.results.map {
                it.toDomainTrailer()
            }
            emit((Resource.Success(data = list)))

        }
        catch (e: HttpException){
            emit(Resource.Error(throwable = Throwable(e.localizedMessage)))
        }
        catch (e: IOException){
            emit(Resource.Error(throwable = Throwable(e.localizedMessage)))
        }
        catch (e: Exception){
            emit(Resource.Error(throwable = Throwable(e.localizedMessage)))
        }

    }
}