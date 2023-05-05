package com.example.catbook.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatImageRemoteData(
    val breeds: List<CatBreedRemoteData>?,
    val id: String?,
    val url: String?,
    val width: Long?,
    val height: Long?,
)