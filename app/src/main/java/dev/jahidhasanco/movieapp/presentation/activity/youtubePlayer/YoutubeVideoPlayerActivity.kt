package dev.jahidhasanco.movieapp.presentation.activity.youtubePlayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint
import dev.jahidhasanco.movieapp.R
import dev.jahidhasanco.movieapp.databinding.ActivityYoutubeVideoPlayerBinding
import dev.jahidhasanco.movieapp.utils.Resource

@AndroidEntryPoint
class YoutubeVideoPlayerActivity : AppCompatActivity() {

    private var _binding:ActivityYoutubeVideoPlayerBinding ? = null
    val binding: ActivityYoutubeVideoPlayerBinding
        get() = _binding!!

    private var movieId: String = ""
    private var  videoId = ""

    private val youtubePlayerViewModel: YoutubePlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_youtube_video_player);


        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        movieId = intent.getStringExtra("MovieIdPass").toString()

        youtubePlayerViewModel.getMovieTrailer(movieId)

        binding.backBtnYoutubeVideoPlayerPage.setOnClickListener {
            onBackPressed()
        }

        youtubePlayerViewModel._trailer.observe(this) { result ->
            if(result is Resource.Success){

                result.let {

                     if(it.data!!.results[0].type == "Trailer"){
                        videoId = it.data.results[0].key
                        binding.movieName.text = it.data.results[0].key
                    }else{
                        videoId = it.data.results[1].key
                        binding.movieName.text = it.data.results[0].key
                    }
                    binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.loadVideo(videoId, 0f)
                        }
                    })
                }

            }
        }
    }


}