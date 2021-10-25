package dev.jahidhasanco.movieapp.domain.model.trailer

import com.google.gson.annotations.SerializedName

data class TrailerData(

    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("official")
    val official: Boolean,
    @SerializedName("type")
    val type: String
) {
}