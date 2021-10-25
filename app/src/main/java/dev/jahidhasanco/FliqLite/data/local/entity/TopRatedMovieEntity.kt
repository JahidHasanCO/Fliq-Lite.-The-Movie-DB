package dev.jahidhasanco.FliqLite.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "topRatedMovies")
data class TopRatedMovieEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("id")
    val idMovie: Int
)