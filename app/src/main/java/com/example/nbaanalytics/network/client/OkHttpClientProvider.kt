package com.example.nbaanalytics.network.client

import android.content.Context
import com.example.nbaanalytics.network.interceptors.OfflineInterceptor
import com.example.nbaanalytics.network.interceptors.OnlineInterceptor
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class OkHttpClientProvider @Inject constructor(
    @ApplicationContext private val context: Context,
    private val offlineInterceptor: OfflineInterceptor,
    private val onlineInterceptor: OnlineInterceptor
) {
    fun getOkHttpClientBuilder(): OkHttpClient {
        val logging = HttpLoggingInterceptor()

        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val cache = okhttp3.Cache(context.cacheDir, cacheSize)

        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        return OkHttpClient
            .Builder()
            .addInterceptor(offlineInterceptor)
            .addNetworkInterceptor(onlineInterceptor)
            .addInterceptor(logging)
            .cache(cache)
            .build()

    }
}