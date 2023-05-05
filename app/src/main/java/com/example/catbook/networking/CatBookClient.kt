package com.example.catbook.networking

class CatBookClient : BaseClient() {
    val catBookService: CatBookService by lazy {
        getRetrofit(
            BASE_URL,
            okHttpClient
        ).create(
            CatBookService::class.java
        )
    }

    companion object {
        const val BASE_URL = "https://api.thecatapi.com"
    }
}