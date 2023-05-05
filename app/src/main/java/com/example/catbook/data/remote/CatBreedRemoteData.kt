package com.example.catbook.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatBreedRemoteData(
    val id: String?,
    val name: String?,
    val temperament: String?,
    val origin: String?,
    @Json(name = "country_codes")
    val countryCodes: String?,
    val description: String?,
    @Json(name = "life_span")
    val lifeSpan: String?,
    @Json(name = "wikipedia_url")
    val wikipediaUrl: String?,
    @Json(name = "image")
    val catImage: CatImage?,
)

@JsonClass(generateAdapter = true)
data class CatImage(
    val id: String?,
    val url: String?,
    val width: Long?,
    val height: Long?,
)