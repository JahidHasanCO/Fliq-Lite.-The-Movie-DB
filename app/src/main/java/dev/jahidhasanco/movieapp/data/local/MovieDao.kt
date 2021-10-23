package dev.jahidhasanco.movieapp.data.local

import androidx.room.Dao
import androidx.room.Query
import dev.jahidhasanco.movieapp.domain.model.movie.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    suspend fun getPopularMovies() : Movie

}