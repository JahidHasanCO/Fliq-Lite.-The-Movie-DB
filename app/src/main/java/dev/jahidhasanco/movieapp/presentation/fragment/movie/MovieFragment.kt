package dev.jahidhasanco.movieapp.presentation.fragment.movie

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import dagger.hilt.android.AndroidEntryPoint
import dev.jahidhasanco.movieapp.databinding.FragmentMovieBinding
import kotlinx.coroutines.flow.collect
import kotlin.math.abs


@AndroidEntryPoint
class MovieFragment : Fragment() {


    private var _binding: FragmentMovieBinding? = null
    val binding: FragmentMovieBinding
        get() = _binding!!

    private val movieAdapter = MovieAdapter()
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

        movieViewModel.upComingMovies("", 1)
        movieViewModel.popularMovies("", 1)

        binding.slider.apply {
            setSliderAdapter(movieSliderAdapter)
            setIndicatorAnimation(IndicatorAnimationType.SWAP)
            setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
            startAutoCycle()
        }

        binding.popularMovieRecycler.apply {
            adapter = movieAdapter
        }



        lifecycle.coroutineScope.launchWhenCreated {

            movieViewModel.upcomingMovieList.collect {

                if (it.isLoading){
                    hideLayout()
                }

                it.data?.let { it ->

                    if (it.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    movieSliderAdapter.setContentList(it.toMutableList())

                }


            }

        }

        lifecycle.coroutineScope.launchWhenCreated {

            movieViewModel.popularMovieList.collect {

                if (it.isLoading){
                    hideLayout()
                }


                it.data?.let { it ->

                    if (it.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                    }

                    movieAdapter.setContentList(it.toMutableList())
                    showLayout()
                }
            }


        }



    }

    private fun showLayout() {
        binding.progress.visibility = View.GONE
        binding.fullContainer.visibility = View.VISIBLE
    }

    private fun hideLayout() {
        binding.fullContainer.visibility = View.GONE
        binding.progress.visibility = View.VISIBLE
    }


}