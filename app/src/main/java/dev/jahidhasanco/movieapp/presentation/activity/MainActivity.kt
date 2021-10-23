package dev.jahidhasanco.movieapp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import dev.jahidhasanco.movieapp.R
import dev.jahidhasanco.movieapp.databinding.ActivityMainBinding
import dev.jahidhasanco.movieapp.presentation.fragment.movie.MovieFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MovieFragment())
            .commit()
    }
}