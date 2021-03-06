package dev.jahidhasanco.movieapp.data.model.movieDetails


import com.google.gson.annotations.SerializedName

data class BelongsToCollectionDTO(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String
)