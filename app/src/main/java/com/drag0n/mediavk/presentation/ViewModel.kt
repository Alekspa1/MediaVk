package com.drag0n.mediavk.presentation

import android.app.Application

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drag0n.mediavk.domain.model.Movie

import com.drag0n.mediavk.domain.useCase.GetRandomMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val getRandomMoviesUseCase: GetRandomMoviesUseCase,
    private val context: Application,
) : ViewModel() {
    var videoUrl: String? = null
    var playbackPosition: Long = 0
    var isPlaying: Boolean = false
    private val _randomListMovie = MutableLiveData<Movie>()
    val randomListMovie: LiveData<Movie> = _randomListMovie
    val load = MutableLiveData<Boolean>()


    fun getRandomMoview() {
        viewModelScope.launch {
            try {
                getRandomMoviesUseCase.exum()?.let { _randomListMovie.postValue(it) }
                load.postValue(false)
            }
            catch (e: Exception){
                Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
                load.postValue(false)

            }

        }
    }


}