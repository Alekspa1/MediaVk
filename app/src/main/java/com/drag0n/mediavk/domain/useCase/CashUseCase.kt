package com.drag0n.mediavk.domain.useCase

import android.app.Application
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import javax.inject.Inject
import javax.inject.Singleton

@UnstableApi
@Singleton
class CashUseCase @Inject constructor(context: Application) {

    private val cacheSize = 100 * 1024 * 1024
    private val cache = SimpleCache(context.cacheDir, LeastRecentlyUsedCacheEvictor(cacheSize.toLong()))


    val dataSourceFactory = CacheDataSource.Factory()
        .setCache(cache)
        .setUpstreamDataSourceFactory(DefaultHttpDataSource.Factory())
}