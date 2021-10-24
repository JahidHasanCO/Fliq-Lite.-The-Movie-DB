package dev.jahidhasanco.movieapp.presentation.fragment.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jahidhasanco.movieapp.data.local.entity.PopularMovieEntity
import dev.jahidhasanco.movieapp.data.local.entity.UpcomingMovieEntity
import dev.jahidhasanco.movieapp.domain.repository.PopularMovieRepository
import dev.jahidhasanco.movieapp.domain.repository.UpComingMovieRepository
import dev.jahidhasanco.movieapp.utils.Resource
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
@Inject
constructor
    (
    private val getPopularMovies: PopularMovieRepository,
    private val getUpComingMovies: UpComingMovieRepository
) :
    ViewModel() {


    lateinit var _upcomingMovies : LiveData<Resource<List<UpcomingMovieEntity>>>

    lateinit var _popularMovies : LiveData<Resource<List<PopularMovieEntity>>>

    fun getUpcomingMovies(lang:String,page:Int){
       _upcomingMovies = getUpComingMovies.getUpComingMovies(lang, page).asLiveData()
    }

    fun getPopularMovies(lang:String,page:Int){
        _popularMovies = getPopularMovies.getPopularMovies(lang, page).asLiveData()
    }

}