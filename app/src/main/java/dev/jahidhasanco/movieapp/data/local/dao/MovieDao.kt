package dev.jahidhasanco.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.jahidhasanco.movieapp.domain.model.movie.Movie


@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    suspend fun getAllPopularMovies() : List<Movie>

    @Query("DELETE FROM movies")
    suspend fun clearAllPopularMovies()

    @Insert
    suspend fun addPopularMovie(movie: List<Movie>)
}