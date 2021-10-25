package dev.jahidhasanco.FliqLite.presentation.activity.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jahidhasanco.FliqLite.domain.repository.MovieDetailsRepository
import dev.jahidhasanco.FliqLite.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel
@Inject
constructor(private val movieDetailsRepository: MovieDetailsRepository):ViewModel()
{
    private val _getMovieDetails = MutableStateFlow(MovieDetailsState())
    var _movieDetails : StateFlow<MovieDetailsState> = _getMovieDetails


    fun getMovieDetails(id: String) {
        movieDetailsRepository.getMovieDetails(id).onEach {
            when(it){
                is Resource.Loading ->{

                    _getMovieDetails.value = MovieDetailsState(isLoading = true)

                }
                is Resource.Error ->{
                    _getMovieDetails.value = MovieDetailsState(error = it.error.toString()?:"")

                }
                is Resource.Success ->{

                    _getMovieDetails.value = MovieDetailsState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}