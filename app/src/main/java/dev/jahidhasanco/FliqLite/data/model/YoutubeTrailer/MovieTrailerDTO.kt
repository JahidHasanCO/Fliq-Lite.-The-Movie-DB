package dev.jahidhasanco.FliqLite.data.model.YoutubeTrailer

import com.google.gson.annotations.SerializedName

data class MovieTrailerDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<ResultTrailerDTO>
)