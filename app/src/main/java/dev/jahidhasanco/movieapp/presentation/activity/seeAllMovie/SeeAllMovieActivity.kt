package dev.jahidhasanco.movieapp.presentation.activity.seeAllMovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import dagger.hilt.android.AndroidEntryPoint
import dev.jahidhasanco.movieapp.R
import dev.jahidhasanco.movieapp.databinding.ActivitySeeAllMovieBinding
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class SeeAllMovieActivity : AppCompatActivity() {


    private var _binding: ActivitySeeAllMovieBinding? = null
    val binding: ActivitySeeAllMovieBinding
        get() = _binding!!

    @Inject
    lateinit var seeAllPopularMoviesAdapter : SeeAllPopularMoviesAdapter

    private val seeAllMovieViewModel: SeeAllMovieViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_see_all_movie)

        initRecyclerview()



        lifecycleScope.launchWhenStarted  {
            seeAllMovieViewModel.getAllPopularMovies.collectLatest { response->
                seeAllPopularMoviesAdapter.submitData(response)
            }
        }

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }

    }

    private fun initRecyclerview() {

            binding.seeALLMovieRec.apply {
                setHasFixedSize(true)
                adapter = seeAllPopularMoviesAdapter.withLoadStateHeaderAndFooter(
                    header = LoaderStateAdapter { seeAllPopularMoviesAdapter :: retry},
                    footer = LoaderStateAdapter{seeAllPopularMoviesAdapter :: retry}
                )
                itemAnimator = DefaultItemAnimator()
            }
    }
}