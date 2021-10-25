package dev.jahidhasanco.movieapp.presentation.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import dev.jahidhasanco.movieapp.R
import dev.jahidhasanco.movieapp.databinding.ActivityMainBinding
import dev.jahidhasanco.movieapp.presentation.fragment.movie.MovieFragment
import dev.jahidhasanco.movieapp.utils.NetworkUtils

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (!NetworkUtils.isInternetAvailable(this)) {
            binding.noInterNetLayout.visibility = View.VISIBLE
        } else {
            binding.noInterNetLayout.visibility = View.GONE
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MovieFragment())
            .commit()

    }
}