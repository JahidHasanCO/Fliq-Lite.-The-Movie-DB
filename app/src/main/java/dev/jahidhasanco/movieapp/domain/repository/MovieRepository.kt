package dev.jahidhasanco.movieapp.domain.repository

import dev.jahidhasanco.movieapp.data.model.movie.MoviesDTO

interface MovieRepository {

    suspend fun getUpcomingMovies(lang: String, page: Int): MoviesDTO
}