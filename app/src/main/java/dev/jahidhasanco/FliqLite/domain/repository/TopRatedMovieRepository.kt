package dev.jahidhasanco.FliqLite.domain.repository


import androidx.room.withTransaction
import dev.jahidhasanco.FliqLite.data.local.AppDataBase
import dev.jahidhasanco.FliqLite.data.model.movie.toTopRatedMovieEntity
import dev.jahidhasanco.FliqLite.data.remote.ApiService

import dev.jahidhasanco.FliqLite.utils.networkBoundResource
import javax.inject.Inject

class TopRatedMovieRepository
@Inject
constructor(
    private val appDataBase: AppDataBase,
    private val apiService: ApiService,
) {

    private val _topRatedMovieDao = appDataBase.getTopRatedMovieDao()


    fun getTopRatedMovies(lang: String, page: Int) = networkBoundResource(
        query = {
            _topRatedMovieDao.getAllTopRatedMovies()
        },
        fetch = {
            apiService.getTopRatedMovies(lang, page)
        },
        saveFetchResult = {
            appDataBase.withTransaction {
                _topRatedMovieDao.clearTopRatedMovies()
                _topRatedMovieDao.addTopRatedMovie(it.results.map {
                    it.toTopRatedMovieEntity()
                })
            }
        }
    )
}