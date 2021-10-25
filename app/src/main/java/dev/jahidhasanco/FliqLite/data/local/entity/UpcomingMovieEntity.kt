package dev.jahidhasanco.FliqLite.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "upComingMovies")
data class UpcomingMovieEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("id")
    val idMovie: Int

)