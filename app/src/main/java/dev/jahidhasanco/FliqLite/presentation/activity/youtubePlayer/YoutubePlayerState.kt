package dev.jahidhasanco.FliqLite.presentation.activity.youtubePlayer

import dev.jahidhasanco.FliqLite.domain.model.trailer.TrailerData

data class YoutubePlayerState(
    val data: List<TrailerData>? = null,
    val error: String = "",
    val isLoading: Boolean = false
) {
}