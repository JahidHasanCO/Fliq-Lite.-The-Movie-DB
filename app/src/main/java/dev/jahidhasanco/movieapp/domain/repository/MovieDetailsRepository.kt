package dev.jahidhasanco.movieapp.domain.repository


import dev.jahidhasanco.movieapp.data.model.movieDetails.MovieDetailsDTO
import dev.jahidhasanco.movieapp.data.remote.ApiService
import dev.jahidhasanco.movieapp.utils.Resource

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

            val response = apiService.getMovieDetails(id,"")

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