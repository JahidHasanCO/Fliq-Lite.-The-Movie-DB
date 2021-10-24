package dev.jahidhasanco.movieapp.domain.repository



import dev.jahidhasanco.movieapp.data.model.YoutubeTrailer.MovieTrailer
import dev.jahidhasanco.movieapp.data.remote.ApiService
import dev.jahidhasanco.movieapp.utils.Resource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieTrailerRepository
@Inject
constructor(
    private val apiService: ApiService,
) {

    operator fun invoke(id: String): Flow<Resource<MovieTrailer>> = flow {

        try {

            emit(Resource.Loading())

            val response = apiService.getMovieTrailer(id,"")

            emit((Resource.Success(data = response)))

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