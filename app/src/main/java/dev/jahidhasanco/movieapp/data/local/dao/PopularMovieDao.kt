package dev.jahidhasanco.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.jahidhasanco.movieapp.data.local.entity.PopularMovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface PopularMovieDao {

    @Query("SELECT * FROM popularMovies")
    fun getAllPopularMovies() : Flow<List<PopularMovieEntity>>

    @Query("DELETE FROM popularMovies")
    suspend fun clearAllPopularMovies()

    @Insert
    suspend fun addPopularMovie(movie: List<PopularMovieEntity>)
}