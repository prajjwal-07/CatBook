package com.example.catbook.networking

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    private val API_KEY_HEADER = "x-api-key"
    private val CAT_API_KEY =
        "live_ZHTi0vTAS050efvdemRLbLAYCfINb6iEsS3ZlMjZuBeapuYT4Yp11I9N4XUYCFwv"

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader(API_KEY_HEADER, CAT_API_KEY)
        return chain.proceed(requestBuilder.build())
    }
}