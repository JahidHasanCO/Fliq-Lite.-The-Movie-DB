package dev.jahidhasanco.FliqLite.presentation.fragment.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jahidhasanco.FliqLite.data.local.entity.PopularMovieEntity
import dev.jahidhasanco.FliqLite.data.local.entity.TopRatedMovieEntity
import dev.jahidhasanco.FliqLite.data.local.entity.UpcomingMovieEntity
import dev.jahidhasanco.FliqLite.domain.repository.PopularMovieRepository
import dev.jahidhasanco.FliqLite.domain.repository.TopRatedMovieRepository
import dev.jahidhasanco.FliqLite.domain.repository.UpComingMovieRepository
import dev.jahidhasanco.FliqLite.utils.Resource
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
@Inject
constructor
    (
    private val getPopularMovies: PopularMovieRepository,
    private val getUpComingMovies: UpComingMovieRepository,
    private val getTopRatedMovies: TopRatedMovieRepository
) :
    ViewModel() {


    lateinit var _upcomingMovies: LiveData<Resource<List<UpcomingMovieEntity>>>

    lateinit var _popularMovies: LiveData<Resource<List<PopularMovieEntity>>>

    lateinit var _topRatedMovies: LiveData<Resource<List<TopRatedMovieEntity>>>

    fun getUpcomingMovies(lang: String, page: Int) {
        _upcomingMovies = getUpComingMovies.getUpComingMovies(lang, page).asLiveData()
    }

    fun getPopularMovies(lang: String, page: Int) {
        _popularMovies = getPopularMovies.getPopularMovies(lang, page).asLiveData()
    }

    fun getTopRatedMovies(lang: String, page: Int) {
        _topRatedMovies = getTopRatedMovies.getTopRatedMovies(lang, page).asLiveData()
    }

}