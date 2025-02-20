package com.drag0n.mediavk.domain.repository

import com.drag0n.mediavk.domain.model.Movie
import retrofit2.Response
import retrofit2.http.GET

interface ApiRepository {
    @GET("poudyalanil/ca84582cbeb4fc123a13290a586da925/raw/14a27bd0bcd0cd323b35ad79cf3b493dddf6216b/")
    suspend fun getRandomMovie() : Response<Movie>
}