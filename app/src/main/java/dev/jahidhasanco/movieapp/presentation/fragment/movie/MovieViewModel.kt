package dev.jahidhasanco.movieapp.presentation.fragment.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jahidhasanco.movieapp.domain.use_case.GetMovieRepositoryUseCase
import dev.jahidhasanco.movieapp.domain.use_case.GetUpComingMovies_UseCase
import dev.jahidhasanco.movieapp.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
@Inject
constructor
    (
    private val getMovieRepositoryUseCase: GetMovieRepositoryUseCase,
    private val getupcomingmoviesUsecase: GetUpComingMovies_UseCase
) :
    ViewModel() {


    private val _upcomingMovieList =
        MutableStateFlow<UpcomingMovieNetworkState>(UpcomingMovieNetworkState())

    val upcomingMovieList: StateFlow<UpcomingMovieNetworkState> = _upcomingMovieList

    private val _popularMovieList = MutableStateFlow<MovieNetworkState>(MovieNetworkState())

    val popularMovieList: StateFlow<MovieNetworkState> = _popularMovieList


    fun upComingMovies(lang: String, page: Int) {
        getupcomingmoviesUsecase.invoke(lang, page).onEach {
            when (it) {
                is Resource.Loading -> {

                    _upcomingMovieList.value = UpcomingMovieNetworkState(isLoading = true)

                }
                is Resource.Error -> {
                    _upcomingMovieList.value = UpcomingMovieNetworkState(error = it.message ?: "")

                }
                is Resource.Success -> {

                    _upcomingMovieList.value = UpcomingMovieNetworkState(data = it.data)
                }

            }
        }.launchIn(viewModelScope)
    }


    fun popularMovies(lang: String, page: Int) {
        getMovieRepositoryUseCase.getPopularMovies(lang, page).onEach {
            when (it) {
                is Resource.Loading -> {

                    _popularMovieList.value = MovieNetworkState(isLoading = true)

                }
                is Resource.Error -> {
                    _popularMovieList.value = MovieNetworkState(error = it.message ?: "")

                }
                is Resource.Success -> {

                    _popularMovieList.value = MovieNetworkState(data = it.data)
                }

            }
        }.launchIn(viewModelScope)
    }

}