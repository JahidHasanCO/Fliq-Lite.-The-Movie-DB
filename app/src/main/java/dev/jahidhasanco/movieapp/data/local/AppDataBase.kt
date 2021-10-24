package dev.jahidhasanco.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.jahidhasanco.movieapp.data.local.dao.PopularMovieDao
import dev.jahidhasanco.movieapp.data.local.dao.UpComingMovieDao
import dev.jahidhasanco.movieapp.data.local.entity.PopularMovieEntity
import dev.jahidhasanco.movieapp.data.local.entity.UpcomingMovieEntity

@Database(
    entities = [PopularMovieEntity::class,UpcomingMovieEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDataBase : RoomDatabase(){

    abstract fun getPopularMovieDao(): PopularMovieDao

    abstract fun getUpcomingMovieDao(): UpComingMovieDao

}