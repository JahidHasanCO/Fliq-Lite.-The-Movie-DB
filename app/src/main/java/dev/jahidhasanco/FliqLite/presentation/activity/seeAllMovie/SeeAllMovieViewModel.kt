package dev.jahidhasanco.FliqLite.presentation.activity.seeAllMovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jahidhasanco.FliqLite.data.local.entity.PopularMovieEntity
import dev.jahidhasanco.FliqLite.data.remote.ApiService
import dev.jahidhasanco.FliqLite.domain.repository.SeeAllPopularMoviePageSource
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