package dev.jahidhasanco.movieapp.data.model.YoutubeTrailer

import com.google.gson.annotations.SerializedName
import dev.jahidhasanco.movieapp.domain.model.trailer.TrailerData

data class ResultTrailerDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("official")
    val official: Boolean,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("type")
    val type: String
)

fun ResultTrailerDTO.toDomainTrailer(): TrailerData {
    return TrailerData(
        key = this.key, name = this.name, official = this.official, type = this.type
    )
}