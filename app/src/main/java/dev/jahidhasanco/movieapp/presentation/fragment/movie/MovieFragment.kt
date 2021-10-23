package dev.jahidhasanco.movieapp.presentation.fragment.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import dagger.hilt.android.AndroidEntryPoint
import dev.jahidhasanco.movieapp.databinding.FragmentMovieBinding
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MovieFragment : Fragment() {


    private var _binding: FragmentMovieBinding? = null
    val binding: FragmentMovieBinding
        get() = _binding!!

    private val movieAdapter = MovieAdapter()
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMovieBinding.inflate(inflater,container,false)

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        movieViewModel.upComingMovies("",1)

        binding.recycler.apply {
            adapter = movieAdapter
        }

        lifecycle.coroutineScope.launchWhenCreated {
            movieViewModel.upcomingMovieList.collect {

                if (it.isLoading) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progress.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progress.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }

                it.data?.let { it ->

                    if (it.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    binding.progress.visibility = View.GONE
                    movieAdapter.setContentList(it.toMutableList())

                }


            }
        }


    }

}