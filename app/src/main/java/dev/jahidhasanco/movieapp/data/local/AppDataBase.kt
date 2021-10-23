package dev.jahidhasanco.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.jahidhasanco.movieapp.domain.model.movie.Movie

@Database(
    entities = [Movie::class], // Tell the database the entries will hold data of this type
    version = 1
)
abstract class AppDataBase : RoomDatabase(){

    abstract fun getMovieDao(): MovieDao

}