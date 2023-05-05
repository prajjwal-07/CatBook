package com.example.catbook.di.module

import com.example.catbook.networking.CatBookClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CatBookNetworkingBuilderModule {

    @Provides
    @Singleton
    fun provideCatBookService() = CatBookClient().catBookService
}