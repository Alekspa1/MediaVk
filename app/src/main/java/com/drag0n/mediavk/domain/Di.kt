package com.drag0n.mediavk.domain

import com.drag0n.mediavk.data.MoviesRepositoryImp
import com.drag0n.mediavk.domain.Const.BASE_URL
import com.drag0n.mediavk.domain.repository.ApiRepository
import com.drag0n.mediavk.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Di {


    @Provides
    @Singleton
    fun providesMoviesImp() : MoviesRepository{
        return MoviesRepositoryImp(providesMoviesRepository())
    }

    @Provides
    @Singleton
    fun providesMoviesRepository() : ApiRepository {
        val baseUrl = BASE_URL
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl).client(client)
            .build()
        return retrofit.create(ApiRepository::class.java)
    }



}