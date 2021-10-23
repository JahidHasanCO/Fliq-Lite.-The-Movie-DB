package dev.jahidhasanco.movieapp.domain.use_case

import android.content.Context
import dev.jahidhasanco.movieapp.data.local.AppDataBase
import dev.jahidhasanco.movieapp.data.local.entity.UpcomingMovieEntity
import dev.jahidhasanco.movieapp.data.model.movie.toDomainMovie
import dev.jahidhasanco.movieapp.data.model.movie.toUpComingMovieEntity
import dev.jahidhasanco.movieapp.domain.repository.MovieRepository
import dev.jahidhasanco.movieapp.utils.NetworkUtils
import dev.jahidhasanco.movieapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class GetUpComingMovies_UseCase
@Inject
constructor(
    private val repository: MovieRepository,
    private val appDataBase: AppDataBase,
    private val applicationContext: Context
) {
    operator fun invoke(lang: String, page: Int): Flow<Resource<List<UpcomingMovieEntity>>> = flow {
        val movies: List<UpcomingMovieEntity> = appDataBase.getUpcomingMovieDao().getAllUpComingMovies()
        if (NetworkUtils.isInternetAvailable(applicationContext)) {

            emit(Resource.Loading())

            val response = repository.getUpcomingMovies(lang, page)

            val list =
                if (response.results.isNullOrEmpty()) emptyList<UpcomingMovieEntity>() else response.results.map {
                    it.toUpComingMovieEntity()
                }

            appDataBase.getUpcomingMovieDao().clearAllUpcomingMovies()
            appDataBase.getUpcomingMovieDao().addUpcomingMovie(list)
            emit((Resource.Success(data = list)))

        } else {
            if (movies.isNullOrEmpty()) {
                emit((Resource.Success(data = movies)))
            }

        }

    }

}