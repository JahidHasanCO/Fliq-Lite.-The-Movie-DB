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

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = ContextCompat.getColor(this,R.color.white)
        };

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MovieFragment())
            .commit()

    }
}