package dev.jahidhasanco.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.jahidhasanco.movieapp.data.local.entity.UpcomingMovieEntity


@Dao
interface UpComingMovieDao
{
    @Query("SELECT * FROM upComingMovies")
    suspend fun getAllUpComingMovies() : List<UpcomingMovieEntity>

    @Query("DELETE FROM upComingMovies")
    suspend fun clearAllUpcomingMovies()

    @Insert
    suspend fun addUpcomingMovie(movie: List<UpcomingMovieEntity>)
}