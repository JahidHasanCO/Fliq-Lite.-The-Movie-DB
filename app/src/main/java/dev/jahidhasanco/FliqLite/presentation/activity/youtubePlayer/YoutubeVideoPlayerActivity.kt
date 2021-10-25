package dev.jahidhasanco.FliqLite.presentation.activity.youtubePlayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.coroutineScope
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint
import dev.jahidhasanco.FliqLite.R
import dev.jahidhasanco.FliqLite.databinding.ActivityYoutubeVideoPlayerBinding
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class YoutubeVideoPlayerActivity : AppCompatActivity() {

    private var _binding: ActivityYoutubeVideoPlayerBinding? = null
    val binding: ActivityYoutubeVideoPlayerBinding
        get() = _binding!!

    private var movieId: String = ""
    private var videoId = ""

    private val youtubePlayerViewModel: YoutubePlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_youtube_video_player)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        movieId = intent.getStringExtra("MovieIdPass").toString()

        youtubePlayerViewModel.getMovieTrailer(movieId)
        lifecycle.addObserver(binding.youtubePlayerView)
        binding.backBtnYoutubeVideoPlayerPage.setOnClickListener {
            onBackPressed()
        }

        lifecycle.coroutineScope.launchWhenCreated {
            youtubePlayerViewModel._trailer.collect {
//                if (it.isLoading) {
//                    binding.nothingFound.visibility = View.GONE
//                    binding.progressMealSearch.visibility = View.VISIBLE
//                }
//                if (it.error.isNotBlank()) {
//                    binding.nothingFound.visibility = View.GONE
//                    binding.progressMealSearch.visibility = View.GONE
//                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
//                }

                it.data?.let { it ->
                    if (it[0].type == "Trailer") {
                        videoId = it[0].key
                        binding.movieName.text = it[0].name
                    }else{
                        videoId = it[1].key
                        binding.movieName.text = it[1].name
                    }

                    binding.youtubePlayerView.addYouTubePlayerListener(object :
                        AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.loadVideo(videoId, 0f)
                        }
                    })
                }


            }
        }

    }
}


