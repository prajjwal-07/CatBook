package com.example.catbook.networking

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

open class BaseClient {
    protected fun getRetrofit(
        baseUrl: String,
        httpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    protected val okHttpClient by lazy {
        val builder = OkHttpClient().newBuilder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(HeaderInterceptor())
        builder.build()
    }
}