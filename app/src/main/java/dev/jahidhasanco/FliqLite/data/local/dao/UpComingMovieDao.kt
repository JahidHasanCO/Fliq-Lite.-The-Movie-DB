package dev.jahidhasanco.FliqLite.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.jahidhasanco.FliqLite.data.local.entity.UpcomingMovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface UpComingMovieDao {
    @Query("SELECT * FROM upComingMovies")
    fun getAllUpComingMovies(): Flow<List<UpcomingMovieEntity>>

    @Query("DELETE FROM upComingMovies")
    suspend fun clearAllUpcomingMovies()

    @Insert
    suspend fun addUpcomingMovie(movie: List<UpcomingMovieEntity>)
}