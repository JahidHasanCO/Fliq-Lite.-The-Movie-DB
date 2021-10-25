package dev.jahidhasanco.FliqLite.domain.repository


import dev.jahidhasanco.FliqLite.data.model.movieDetails.MovieDetailsDTO
import dev.jahidhasanco.FliqLite.data.remote.ApiService
import dev.jahidhasanco.FliqLite.utils.Resource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieDetailsRepository
@Inject
constructor(
    private val apiService: ApiService
) {

     fun getMovieDetails(id: String): Flow<Resource<MovieDetailsDTO>> = flow{

        try {
            emit(Resource.Loading())

            val response = apiService.getMovieDetails(id,"en-US")

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