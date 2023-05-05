package com.example.catbook.networking

import com.example.catbook.data.remote.CatBreedRemoteData
import com.example.catbook.data.remote.CatImageRemoteData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatBookService {

    @GET("/v1/breeds")
    suspend fun getCatBreed(
        @Query("limit") limit: Int,
        @Query("page") offset: Int,
    ): Response<List<CatBreedRemoteData>>

    @GET("/v1/images/search")
    suspend fun getCatImage(
        @Query("limit") limit: Int,
        @Query("page") offset: Int,
        @Query("breed_ids") breedIds: String,
    ): Response<List<CatImageRemoteData>>


}