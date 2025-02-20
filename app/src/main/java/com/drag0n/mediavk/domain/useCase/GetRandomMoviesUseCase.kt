package com.drag0n.mediavk.domain.useCase

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.drag0n.mediavk.domain.model.Movie
import com.drag0n.mediavk.domain.repository.MoviesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRandomMoviesUseCase @Inject constructor(private val movie: MoviesRepository) {

    suspend fun exum(): Movie? {
        return movie.getRandomMovies()

    }
}