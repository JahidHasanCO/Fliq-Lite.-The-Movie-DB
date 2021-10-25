package dev.jahidhasanco.FliqLite.presentation.activity.youtubePlayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jahidhasanco.FliqLite.domain.repository.MovieTrailerRepository
import dev.jahidhasanco.FliqLite.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class YoutubePlayerViewModel
@Inject
constructor(private val movieTrailerRepository: MovieTrailerRepository)
    :ViewModel()
{
    private val _getTrailer = MutableStateFlow(YoutubePlayerState())
    var _trailer : StateFlow<YoutubePlayerState> = _getTrailer


    fun getMovieTrailer(id: String) {
        movieTrailerRepository.getTrailer(id).onEach {
            when(it){
                is Resource.Loading ->{

                    _getTrailer.value = YoutubePlayerState(isLoading = true)

                }
                is Resource.Error ->{
                    _getTrailer.value = YoutubePlayerState(error = it.error.toString()?:"")

                }
                is Resource.Success ->{

                    _getTrailer.value = YoutubePlayerState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}