package dev.jahidhasanco.movieapp.domain.repository

import android.content.Context
import androidx.room.withTransaction
import dev.jahidhasanco.movieapp.data.local.AppDataBase
import dev.jahidhasanco.movieapp.data.model.movie.toUpComingMovieEntity
import dev.jahidhasanco.movieapp.data.remote.ApiService
import dev.jahidhasanco.movieapp.utils.NetworkUtils
import dev.jahidhasanco.movieapp.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class UpComingMovieRepository
@Inject
constructor(
    private val appDataBase: AppDataBase,
    private val apiService: ApiService
) {


    private val _UpcomingDao = appDataBase.getUpcomingMovieDao()

    fun getUpComingMovies(lang: String, page: Int) = networkBoundResource(
        query = {
            _UpcomingDao.getAllUpComingMovies()
        },
        fetch = {
            apiService.getUpcomingMovies(lang, page)
        },
        saveFetchResult = {
            appDataBase.withTransaction {
                _UpcomingDao.clearAllUpcomingMovies()
                _UpcomingDao.addUpcomingMovie(it.results.map {
                    it.toUpComingMovieEntity()
                })
            }
        }
    )

}