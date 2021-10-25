package dev.jahidhasanco.FliqLite.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import dev.jahidhasanco.FliqLite.R
import dev.jahidhasanco.FliqLite.databinding.ActivityMainBinding
import dev.jahidhasanco.FliqLite.presentation.fragment.movie.MovieFragment
import dev.jahidhasanco.FliqLite.utils.NetworkUtils

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