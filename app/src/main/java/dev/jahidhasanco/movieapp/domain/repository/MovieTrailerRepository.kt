package dev.jahidhasanco.movieapp.domain.repository



import dev.jahidhasanco.movieapp.data.model.YoutubeTrailer.MovieTrailerDTO
import dev.jahidhasanco.movieapp.data.model.YoutubeTrailer.toDomainTrailer
import dev.jahidhasanco.movieapp.data.remote.ApiService
import dev.jahidhasanco.movieapp.domain.model.trailer.TrailerData
import dev.jahidhasanco.movieapp.utils.Resource

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

     fun getTrailer(id: String): Flow<Resource<TrailerData>> = flow{

        try {
            emit(Resource.Loading())

            val response = apiService.getMovieTrailer(id,"")

            val list = if(response.results.isNullOrEmpty()) emptyList<TrailerData>() else response.results.map {
                it.toDomainTrailer()
            }
            emit((Resource.Success(data = list[0])))

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