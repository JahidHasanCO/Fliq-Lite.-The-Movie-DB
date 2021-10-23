package dev.jahidhasanco.movieapp.data.repository

import dev.jahidhasanco.movieapp.data.model.movie.MoviesDTO
import dev.jahidhasanco.movieapp.data.remote.ApiService
import dev.jahidhasanco.movieapp.domain.repository.MovieRepository

class MovieRepositoryImpl(private val apiService: ApiService): MovieRepository {

    override suspend fun getUpcomingMovies(lang: String, page: Int): MoviesDTO {
        return apiService.getUpcomingMovies(lang,page)
    }
}