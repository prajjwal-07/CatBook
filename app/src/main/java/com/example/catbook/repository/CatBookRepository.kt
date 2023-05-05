package com.example.catbook.repository

import com.example.catbook.networking.CatBookService
import javax.inject.Inject

class CatBookRepository @Inject constructor(
    private val catBookService: CatBookService
) {
    suspend fun fetchCatBreed(
        limit: Int,
        page: Int
    ) = catBookService.getCatBreed(limit, page)

    suspend fun fetchCatImage(
        limit: Int,
        page: Int,
        breedIds: String,
    ) = catBookService.getCatImage(limit, page, breedIds)
}