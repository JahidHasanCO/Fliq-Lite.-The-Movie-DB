package dev.jahidhasanco.movieapp.data.model.YoutubeTrailer

import com.google.gson.annotations.SerializedName

data class MovieTrailer(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<ResultTrailer>
)