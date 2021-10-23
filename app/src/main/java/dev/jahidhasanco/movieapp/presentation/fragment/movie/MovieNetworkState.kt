package dev.jahidhasanco.movieapp.presentation.fragment.movie

import dev.jahidhasanco.movieapp.domain.model.movie.Movie

data class MovieNetworkState (
    val data: List<Movie>? = null,
    val error: String = "",
    val isLoading: Boolean = false
)