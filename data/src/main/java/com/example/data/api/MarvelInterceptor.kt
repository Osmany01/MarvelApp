package com.example.data.api

import com.example.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

class MarvelInterceptor(val client: OkHttpClient.Builder): Interceptor {

    companion object {

        const val API_KEY = "apikey"
        const val TIME_STAMP = "ts"
        const val HASH = "hash"
        const val MD5 = "MD5"
    }
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val timeStamp = (System.currentTimeMillis()/1000).toString()
        val hash = (timeStamp.plus(BuildConfig.private_key).plus(BuildConfig.api_key)).toMd5()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(API_KEY, BuildConfig.api_key)
            .addQueryParameter(TIME_STAMP, timeStamp)
            .addQueryParameter(HASH, hash)
            .build()

        val request = originalRequest.newBuilder()
            .url(url).build()

        return chain.proceed(request)
    }
}