package dev.jahidhasanco.movieapp.presentation.fragment.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import dagger.hilt.android.AndroidEntryPoint
import dev.jahidhasanco.movieapp.databinding.FragmentMovieBinding
import dev.jahidhasanco.movieapp.utils.NetworkUtils
import dev.jahidhasanco.movieapp.utils.Resource


@AndroidEntryPoint
class MovieFragment : Fragment() {


    private var _binding: FragmentMovieBinding? = null
    val binding: FragmentMovieBinding
        get() = _binding!!

    private val movieAdapter = PopularMovieAdapter()
    private val movieSliderAdapter = MovieSliderAdapter()
    private val topRatedMovieAdapter = TopRatedMovieAdapter()
    private val movieViewModel: MovieViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMovieBinding.inflate(inflater, container, false)

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (!NetworkUtils.isInternetAvailable(context!!)) {
            binding.noInterNetLayout.visibility = View.VISIBLE
        } else {
            binding.noInterNetLayout.visibility = View.GONE
        }

        movieViewModel.getUpcomingMovies("", 1)
        movieViewModel.getPopularMovies("", 1)
        movieViewModel.getTopRatedMovies("", 1)

        binding.slider.apply {
            setSliderAdapter(movieSliderAdapter)
            setIndicatorAnimation(IndicatorAnimationType.WORM)
            setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            startAutoCycle()
        }

        binding.popularMovieRecycler.apply {
            adapter = movieAdapter
        }

        binding.topRatedMovieRecycler.apply {
            adapter = topRatedMovieAdapter
        }

        fetchData()


    }

    private fun fetchData() {

        movieViewModel._upcomingMovies.observe(this) { result ->
            movieSliderAdapter.setContentList(result.data!!)
            if (result is Resource.Loading || result.data.isNullOrEmpty()) {
                hideLayout()
            }
        }

        movieViewModel._popularMovies.observe(this) { result ->

            movieAdapter.submitList(result.data!!)


        }

        movieViewModel._topRatedMovies.observe(this) { result ->

            topRatedMovieAdapter.submitList(result.data!!)

            if (result is Resource.Success || !result.data.isNullOrEmpty()) {
                showLayout()
            }

        }

    }

    private fun showLayout() {
        binding.shimmerViewContainer.stopShimmer()
        binding.shimmerViewContainer.hideShimmer()
        binding.shimmerViewContainer.visibility = View.GONE
        binding.fullContainer.visibility = View.VISIBLE
    }

    private fun hideLayout() {
        binding.fullContainer.visibility = View.GONE
        binding.nothingFound.visibility = View.GONE
    }


}