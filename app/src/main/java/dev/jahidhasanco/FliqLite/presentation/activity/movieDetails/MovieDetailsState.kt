package dev.jahidhasanco.FliqLite.presentation.activity.movieDetails

import dev.jahidhasanco.FliqLite.data.model.movieDetails.MovieDetailsDTO

data class MovieDetailsState(
    val data: MovieDetailsDTO? = null,
    val error: String = "",
    val isLoading: Boolean = false
)