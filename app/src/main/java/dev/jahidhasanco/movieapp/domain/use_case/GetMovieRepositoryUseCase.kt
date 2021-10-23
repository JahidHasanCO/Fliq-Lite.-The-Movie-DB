package dev.jahidhasanco.movieapp.domain.use_case

import android.content.Context
import dev.jahidhasanco.movieapp.data.local.AppDataBase
import dev.jahidhasanco.movieapp.data.local.MovieDao
import dev.jahidhasanco.movieapp.data.model.movie.MoviesDTO
import dev.jahidhasanco.movieapp.data.model.movie.toDomainMovie
import dev.jahidhasanco.movieapp.domain.model.movie.Movie
import dev.jahidhasanco.movieapp.domain.repository.MovieRepository
import dev.jahidhasanco.movieapp.utils.NetworkUtils
import dev.jahidhasanco.movieapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieRepositoryUseCase
@Inject
constructor(
    private val repository: MovieRepository,
    private val appDataBase: AppDataBase,
    private val applicationContext: Context
) {


    fun getUpcomingMovies(lang: String, page: Int): Flow<Resource<List<Movie>>> = flow {

        try {

            emit(Resource.Loading())

            val response = repository.getUpcomingMovies(lang, page)

            val list =
                if (response.results.isNullOrEmpty()) emptyList<Movie>() else response.results.map {
                    it.toDomainMovie()
                }

            emit((Resource.Success(data = list)))


        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Your Internet Connection"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: ""))
        }

    }

    fun getPopularMovies(lang: String, page: Int): Flow<Resource<List<Movie>>> = flow {

        val movies : List<Movie> = appDataBase.getMovieDao().getAllPopularMovies()

        if (NetworkUtils.isInternetAvailable(applicationContext)) {

            emit(Resource.Loading())

            val responsePopularMovies = repository.getPopularMovies(lang, page)

            val list =
                if (responsePopularMovies.results.isNullOrEmpty()) emptyList<Movie>() else responsePopularMovies.results.map {
                    it.toDomainMovie()
                }
            appDataBase.getMovieDao().clearAllPopularMovies()
            appDataBase.getMovieDao().addPopularMovie(list)
            emit((Resource.Success(data = movies)))


        }
        //        catch (e: HttpException) {
//            emit(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
//        } catch (e: IOException) {
//            emit(Resource.Error(message = e.localizedMessage ?: "Check Your Internet Connection"))
//        } catch (e: Exception) {
//            emit(Resource.Error(message = e.localizedMessage ?: ""))
//        }

        else {
            if(movies.isNullOrEmpty()){
                emit((Resource.Success(data = movies)))
            }

        }

    }

}