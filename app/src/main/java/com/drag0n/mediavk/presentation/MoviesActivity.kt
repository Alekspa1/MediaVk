package com.drag0n.mediavk.presentation


import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.OptIn


import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi

import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory


import com.drag0n.mediavk.databinding.ActivityMoviesBinding
import com.drag0n.mediavk.Const.INTENT_KEY
import dagger.hilt.android.AndroidEntryPoint

@UnstableApi
@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding
    private lateinit var player: ExoPlayer
    private val model: ViewModel by viewModels()



    @OptIn(UnstableApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkOrientation()

        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        player = ExoPlayer.Builder(this)
            .setMediaSourceFactory(DefaultMediaSourceFactory(model.dataSource()))
            .build()
        binding.playerView.player = player



        if (model.videoUrl != null) {
            val mediaItem = MediaItem.fromUri(Uri.parse(model.videoUrl))
            player.setMediaItem(mediaItem)
            player.seekTo(model.playbackPosition)
            player.prepare()
            if (model.isPlaying) {
                player.play()
            }
        }
        else {
            val videoUrl = intent.getStringExtra(INTENT_KEY) ?: return
            model.videoUrl = videoUrl
            val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()
        }






    }
    private fun checkOrientation() {
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            enterFullScreen()
        } else {
            exitFullScreen()
        }
    }

    private fun enterFullScreen() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )
        supportActionBar?.hide()
    }

    private fun exitFullScreen() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        supportActionBar?.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()

    }

    override fun onPause() {
        super.onPause()
        model.playbackPosition = player.currentPosition
        model.isPlaying = player.isPlaying
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        checkOrientation()
    }


}