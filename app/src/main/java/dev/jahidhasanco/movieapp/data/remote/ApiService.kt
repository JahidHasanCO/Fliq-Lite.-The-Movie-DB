package dev.jahidhasanco.movieapp.data.remote

import dev.jahidhasanco.movieapp.data.model.movie.MoviesDTO
import dev.jahidhasanco.movieapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/upcoming?api_key=$API_KEY")
    suspend fun getUpcomingMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): MoviesDTO

    @GET("3/movie/popular?api_key=$API_KEY")
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): MoviesDTO


}