package dev.jahidhasanco.movieapp.domain.model.movie


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("backdrop_path")
    val backdropPath: String
) {
}