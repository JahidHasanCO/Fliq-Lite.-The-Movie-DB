package dev.jahidhasanco.movieapp.presentation.fragment.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.load.engine.Resource
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import dagger.hilt.android.AndroidEntryPoint
import dev.jahidhasanco.movieapp.databinding.FragmentMovieBinding


@AndroidEntryPoint
class MovieFragment : Fragment() {


    private var _binding: FragmentMovieBinding? = null
    val binding: FragmentMovieBinding
        get() = _binding!!

    private val movieAdapter = PopularMovieAdapter()
    private val movieSliderAdapter = MovieSliderAdapter()
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

        movieViewModel.getUpcomingMovies("", 1)
        movieViewModel.getPopularMovies("", 1)

        binding.slider.apply {
            setSliderAdapter(movieSliderAdapter)
            setIndicatorAnimation(IndicatorAnimationType.SWAP)
            setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
            startAutoCycle()
        }

        binding.popularMovieRecycler.apply {
            adapter = movieAdapter
        }



        movieViewModel._upcomingMovies.observe(this) { result ->

            movieSliderAdapter.setContentList(result.data!!)
            hideLayout()
        }

        movieViewModel._popularMovies.observe(this) { result ->

            movieAdapter.submitList(result.data!!)
            showLayout()
//            binding.shimmerViewContainer.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
//            binding.nothingFound.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
//            textViewError.text = result.error?.localizedMessage
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