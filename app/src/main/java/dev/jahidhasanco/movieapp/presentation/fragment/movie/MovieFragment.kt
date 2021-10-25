package dev.jahidhasanco.movieapp.presentation.fragment.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import dagger.hilt.android.AndroidEntryPoint
import dev.jahidhasanco.movieapp.databinding.FragmentMovieBinding
import dev.jahidhasanco.movieapp.presentation.activity.seeAllMovie.SeeAllMovieActivity
import dev.jahidhasanco.movieapp.utils.NetworkUtils
import dev.jahidhasanco.movieapp.utils.Resource


@AndroidEntryPoint
class MovieFragment : Fragment() {


    private var _binding: FragmentMovieBinding? = null
    val binding: FragmentMovieBinding
        get() = _binding!!

    private lateinit var categoryAdapter: CategoryAdapter
    private val movieAdapter = PopularMovieAdapter()
    private lateinit var movieSliderAdapter : MovieSliderAdapter
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
        hideLayout()

        val categories: ArrayList<String> = arrayListOf()
        categories.add("Fantasy")
        categories.add("Adventure")
        categories.add("Action")
        categories.add("Sci-Fi")
        categories.add("Thriller")
        categories.add("Animation")
        categories.add("Horror")

        categoryAdapter = CategoryAdapter(categories)
        movieSliderAdapter = MovieSliderAdapter(context!!)

        binding.swipe.setOnRefreshListener {

            if (NetworkUtils.isInternetAvailable(context!!)) {
                fetchViewModel()
                fetchDataAfterRefresh()
                binding.swipe.isRefreshing = false
            } else {
                binding.swipe.isRefreshing = false
                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
            }

        }

        fetchViewModel()



        binding.popularMovieSeeAll.setOnClickListener {
            startActivity(Intent(context, SeeAllMovieActivity::class.java))
        }

        binding.slider.apply {
            setSliderAdapter(movieSliderAdapter)
            setIndicatorAnimation(IndicatorAnimationType.WORM)
            setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            startAutoCycle()
        }

        binding.popularMovieRecycler.apply {
            adapter = movieAdapter
        }
        binding.categoryRec.apply {
            setHasFixedSize(true)
            adapter = categoryAdapter
        }
        binding.topRatedMovieRecycler.apply {
            adapter = topRatedMovieAdapter
        }



        if (NetworkUtils.isInternetAvailable(context!!)) {
            fetchData()
        } else {
            fetchDataAfterRefresh()
        }


    }

    private fun fetchViewModel() {
        movieViewModel.getUpcomingMovies("", 1)
        movieViewModel.getPopularMovies("", 1)
        movieViewModel.getTopRatedMovies("", 1)
    }

    private fun fetchData() {

        movieViewModel._upcomingMovies.observe(this) { result ->
            movieSliderAdapter.setContentList(result.data!!)
            if (result is Resource.Loading) {
                hideLayout()
            }
        }

        movieViewModel._popularMovies.observe(this) { result ->
            movieAdapter.submitList(result.data!!)
        }

        movieViewModel._topRatedMovies.observe(this) { result ->

            topRatedMovieAdapter.submitList(result.data!!)
            if (result is Resource.Success) {
                binding.shimmerViewContainer.stopShimmer()
                binding.shimmerViewContainer.hideShimmer()
                binding.shimmerViewContainer.visibility = View.GONE
                binding.fullContainer.visibility = View.VISIBLE
            }
        }

    }


    private fun fetchDataAfterRefresh() {

        movieViewModel._upcomingMovies.observe(this) { result ->
            movieSliderAdapter.setContentList(result.data!!)
        }
        movieViewModel._popularMovies.observe(this) { result ->
            movieAdapter.submitList(result.data!!)
        }
        movieViewModel._topRatedMovies.observe(this) { result ->
            topRatedMovieAdapter.submitList(result.data!!)
        }

    }

    private fun hideLayout(){
        binding.shimmerViewContainer.startShimmer()
        binding.shimmerViewContainer.visibility = View.VISIBLE
        binding.fullContainer.visibility = View.INVISIBLE
    }

}