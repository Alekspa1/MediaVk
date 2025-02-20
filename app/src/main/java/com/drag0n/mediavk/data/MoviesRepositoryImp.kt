package com.drag0n.mediavk.data

import android.util.Log
import com.drag0n.mediavk.domain.model.Movie
import com.drag0n.mediavk.domain.repository.ApiRepository
import com.drag0n.mediavk.domain.repository.MoviesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImp @Inject constructor(private val api: ApiRepository) : MoviesRepository {
    override suspend fun getRandomMovies() : Movie? {
        Log.d("MyLog", "fun")
        return if (api.getRandomMovie().isSuccessful) api.getRandomMovie().body()
        else null
    }
}