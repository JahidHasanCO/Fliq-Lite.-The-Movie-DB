package dev.jahidhasanco.FliqLite.domain.repository

import androidx.room.withTransaction
import dev.jahidhasanco.FliqLite.data.local.AppDataBase
import dev.jahidhasanco.FliqLite.data.model.movie.toUpComingMovieEntity
import dev.jahidhasanco.FliqLite.data.remote.ApiService
import dev.jahidhasanco.FliqLite.utils.networkBoundResource
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