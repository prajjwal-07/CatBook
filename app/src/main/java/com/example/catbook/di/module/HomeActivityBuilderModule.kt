package com.example.catbook.di.module

import com.example.catbook.ui.HomeActivity
import com.example.catbook.di.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [
        ViewModelModule::class,
        CatBookNetworkingBuilderModule::class,

    ]
)
abstract class HomeActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun contributeHomeActivity(): HomeActivity
}