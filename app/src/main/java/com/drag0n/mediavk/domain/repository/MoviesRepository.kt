package com.drag0n.mediavk.domain.repository

import com.drag0n.mediavk.domain.model.Movie

interface MoviesRepository {
    suspend fun getRandomMovies() : Movie?
}