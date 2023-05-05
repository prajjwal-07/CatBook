package com.example.catbook.utils

import com.example.catbook.data.local.CatBreedData
import com.example.catbook.data.local.CatImageData
import com.example.catbook.data.remote.CatBreedRemoteData
import com.example.catbook.data.remote.CatImageRemoteData


fun List<CatBreedRemoteData>?.getCatBreedEpoxyData(): List<CatBreedData> {
    return this?.map {
        CatBreedData(
            id = it.id.orEmpty(),
            breedName = it.name.orEmpty(),
            origin = it.origin.orEmpty(),
            temperament = it.temperament.orEmpty()
        )
    } ?: listOf()
}

fun List<CatImageRemoteData>?.getCatImageEpoxyData(): List<CatImageData> {
    return this?.map {
        CatImageData(
            id = it.id.orEmpty(),
            breedName = it.breeds?.firstOrNull()?.name ?: "Breed Not Present",
            imageUrl = it.url.orEmpty()
        )
    } ?: listOf()
}

