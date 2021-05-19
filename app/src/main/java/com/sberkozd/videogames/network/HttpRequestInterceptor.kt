package com.sberkozd.videogames.network

import com.sberkozd.videogames.apiKey
import com.sberkozd.videogames.rapidApiKey
import okhttp3.Interceptor
import okhttp3.Response

internal class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()
        val url = originalUrl.newBuilder()
            .addQueryParameter("key", apiKey)
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
            .addHeader("x-rapidapi-key", rapidApiKey)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}

