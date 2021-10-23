package dev.jahidhasanco.movieapp.presentation.fragment.movie

import dev.jahidhasanco.movieapp.data.local.entity.UpcomingMovieEntity


data class UpcomingMovieNetworkState (
    val data: List<UpcomingMovieEntity>? = null,
    val error: String = "",
    val isLoading: Boolean = false
)