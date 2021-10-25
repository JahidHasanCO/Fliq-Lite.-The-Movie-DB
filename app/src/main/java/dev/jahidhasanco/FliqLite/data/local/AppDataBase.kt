package dev.jahidhasanco.FliqLite.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.jahidhasanco.FliqLite.data.local.dao.PopularMovieDao
import dev.jahidhasanco.FliqLite.data.local.dao.TopRatedMovieDao
import dev.jahidhasanco.FliqLite.data.local.dao.UpComingMovieDao
import dev.jahidhasanco.FliqLite.data.local.entity.PopularMovieEntity
import dev.jahidhasanco.FliqLite.data.local.entity.TopRatedMovieEntity
import dev.jahidhasanco.FliqLite.data.local.entity.UpcomingMovieEntity

@Database(
    entities = [PopularMovieEntity::class, UpcomingMovieEntity::class, TopRatedMovieEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getPopularMovieDao(): PopularMovieDao

    abstract fun getUpcomingMovieDao(): UpComingMovieDao

    abstract fun getTopRatedMovieDao(): TopRatedMovieDao

}