package dev.jahidhasanco.FliqLite.domain.repository


import androidx.room.withTransaction
import dev.jahidhasanco.FliqLite.data.local.AppDataBase
import dev.jahidhasanco.FliqLite.data.model.movie.toPopularMovieEntity
import dev.jahidhasanco.FliqLite.data.remote.ApiService

import dev.jahidhasanco.FliqLite.utils.networkBoundResource
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