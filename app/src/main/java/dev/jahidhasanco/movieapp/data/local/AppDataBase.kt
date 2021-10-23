package dev.jahidhasanco.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [MovieDao::class], // Tell the database the entries will hold data of this type
    version = 1
)

abstract class AppDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}