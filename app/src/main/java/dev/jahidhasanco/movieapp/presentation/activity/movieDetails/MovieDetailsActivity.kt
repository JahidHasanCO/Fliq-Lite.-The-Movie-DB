package dev.jahidhasanco.movieapp.presentation.activity.movieDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.coroutineScope
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import dev.jahidhasanco.movieapp.R
import dev.jahidhasanco.movieapp.databinding.ActivityMovieDetailsBinding
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {

    private var _binding: ActivityMovieDetailsBinding? = null
    val binding: ActivityMovieDetailsBinding
        get() = _binding!!

    private val movieViewModel: MovieDetailsViewModel by viewModels()
    private var movieId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)

        movieId = intent.getStringExtra("MovieIdPass").toString()
        movieViewModel.getMovieDetails(movieId)

        fetchData()
    }
    private fun fetchData() {

        lifecycle.coroutineScope.launchWhenCreated{
            movieViewModel._movieDetails.collect{
                if(it.isLoading){

                }
                it.data?.let {movie ->
                    Glide.with(this@MovieDetailsActivity).load("https://image.tmdb.org/t/p/w780${movie.posterPath}").into(binding.coverImage)
                    Glide.with(this@MovieDetailsActivity).load("https://image.tmdb.org/t/p/w780${movie.posterPath}").into(binding.movieImage)
                }
            }
        }

    }


}