package dev.jahidhasanco.movieapp.data.local.entity

import android.graphics.Bitmap
import android.os.Parcelable
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
    val backdropPath: String

)