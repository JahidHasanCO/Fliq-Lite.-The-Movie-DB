package dev.jahidhasanco.movieapp.domain.repository


import androidx.room.withTransaction
import dev.jahidhasanco.movieapp.data.local.AppDataBase
import dev.jahidhasanco.movieapp.data.model.movie.toTopRatedMovieEntity
import dev.jahidhasanco.movieapp.data.remote.ApiService

import dev.jahidhasanco.movieapp.utils.networkBoundResource
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