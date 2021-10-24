package dev.jahidhasanco.movieapp.presentation.activity.youtubePlayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jahidhasanco.movieapp.data.local.entity.UpcomingMovieEntity
import dev.jahidhasanco.movieapp.data.model.YoutubeTrailer.MovieTrailer
import dev.jahidhasanco.movieapp.domain.repository.MovieTrailerRepository
import dev.jahidhasanco.movieapp.utils.Resource
import javax.inject.Inject

@HiltViewModel
class YoutubePlayerViewModel
@Inject
constructor(private val movieTrailerRepository: MovieTrailerRepository)
    :ViewModel()
{
    lateinit var _trailer: LiveData<Resource<MovieTrailer>>


    fun getMovieTrailer(id: String) {
        _trailer = movieTrailerRepository(id).asLiveData()
    }
}