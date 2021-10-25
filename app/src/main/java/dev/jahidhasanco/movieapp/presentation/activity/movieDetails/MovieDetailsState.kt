package dev.jahidhasanco.movieapp.presentation.activity.movieDetails

import dev.jahidhasanco.movieapp.data.model.movieDetails.MovieDetailsDTO

data class MovieDetailsState(
    val data: MovieDetailsDTO? = null,
    val error: String = "",
    val isLoading: Boolean = false
)