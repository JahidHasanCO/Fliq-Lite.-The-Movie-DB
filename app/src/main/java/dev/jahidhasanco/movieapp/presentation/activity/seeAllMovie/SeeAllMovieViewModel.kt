package dev.jahidhasanco.movieapp.presentation.activity.seeAllMovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jahidhasanco.movieapp.data.local.entity.PopularMovieEntity
import dev.jahidhasanco.movieapp.data.remote.ApiService
import dev.jahidhasanco.movieapp.domain.repository.SeeAllPopularMoviePageSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SeeAllMovieViewModel
@Inject constructor(private val apiService: ApiService) : ViewModel()
{
    val getAllPopularMovies: Flow<PagingData<PopularMovieEntity>> = Pager(config = PagingConfig(20,enablePlaceholders = false)){
        SeeAllPopularMoviePageSource(apiService)
    }.flow.cachedIn(viewModelScope)


}