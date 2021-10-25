package dev.jahidhasanco.FliqLite.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.jahidhasanco.FliqLite.data.local.entity.TopRatedMovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TopRatedMovieDao {

    @Query("SELECT * FROM topRatedMovies")
    fun getAllTopRatedMovies(): Flow<List<TopRatedMovieEntity>>

    @Query("DELETE FROM topRatedMovies")
    suspend fun clearTopRatedMovies()

    @Insert
    suspend fun addTopRatedMovie(movie: List<TopRatedMovieEntity>)
}