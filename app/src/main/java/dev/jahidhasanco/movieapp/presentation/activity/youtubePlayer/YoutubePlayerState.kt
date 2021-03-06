package dev.jahidhasanco.movieapp.presentation.activity.youtubePlayer

import dev.jahidhasanco.movieapp.domain.model.trailer.TrailerData

data class YoutubePlayerState(
    val data: List<TrailerData>? = null,
    val error: String = "",
    val isLoading: Boolean = false
) {
}