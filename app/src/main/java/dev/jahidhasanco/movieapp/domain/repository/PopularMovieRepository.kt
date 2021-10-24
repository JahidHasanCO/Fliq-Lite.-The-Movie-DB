package dev.jahidhasanco.movieapp.domain.repository


import androidx.room.withTransaction
import dev.jahidhasanco.movieapp.data.local.AppDataBase
import dev.jahidhasanco.movieapp.data.model.movie.toPopularMovieEntity
import dev.jahidhasanco.movieapp.data.remote.ApiService

import dev.jahidhasanco.movieapp.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class PopularMovieRepository

@Inject
constructor(
    private val appDataBase: AppDataBase,
    private val apiService: ApiService,
) {

    private val _popularMovieDao = appDataBase.getPopularMovieDao()


    fun getPopularMovies(lang: String, page: Int) = networkBoundResource(
        query = {
            _popularMovieDao.getAllPopularMovies()
        },
        fetch = {
            apiService.getPopularMovies(lang, page)
        },
        saveFetchResult = {
            appDataBase.withTransaction {
                _popularMovieDao.clearAllPopularMovies()
                _popularMovieDao.addPopularMovie(it.results.map {
                    it.toPopularMovieEntity()
                })
            }
        }
    )
}