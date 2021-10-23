package dev.jahidhasanco.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.jahidhasanco.movieapp.data.local.dao.MovieDao
import dev.jahidhasanco.movieapp.data.local.dao.UpComingMovieDao
import dev.jahidhasanco.movieapp.data.local.entity.UpcomingMovieEntity
import dev.jahidhasanco.movieapp.domain.model.movie.Movie

@Database(
    entities = [Movie::class,UpcomingMovieEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDataBase : RoomDatabase(){

    abstract fun getMovieDao(): MovieDao

    abstract fun getUpcomingMovieDao(): UpComingMovieDao

}