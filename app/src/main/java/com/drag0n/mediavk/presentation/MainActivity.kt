package com.drag0n.mediavk.presentation


import android.content.Intent
import android.os.Bundle

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.drag0n.mediavk.databinding.ActivityMainBinding
import com.drag0n.mediavk.Const.INTENT_KEY
import com.drag0n.mediavk.domain.model.MovieItem
import com.drag0n.mediavk.presentation.adapters.MoviesAdapter


import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MoviesAdapter.OnClick {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var adapter: MoviesAdapter



    private val model: ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        model.getRandomMoview()


        val rcView = binding.rcView
        rcView.layoutManager = LinearLayoutManager(this)

        model.randomListMovie.observe(this) { list ->
            adapter = MoviesAdapter(list, this)
            rcView.adapter = adapter
        }

        model.load.observe(this) {
            binding.swipeRefreshLayout.isRefreshing = it
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            model.getRandomMoview()
        }

        binding.swipeRefreshLayout.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )


    }

    override fun onClik(item: MovieItem) {
        val intent = Intent(this, MoviesActivity::class.java)
        intent.putExtra(INTENT_KEY, item.videoUrl)
        startActivity(intent)
    }

}