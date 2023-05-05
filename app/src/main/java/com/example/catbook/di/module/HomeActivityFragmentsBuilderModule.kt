package com.example.catbook.di.module

import com.example.catbook.di.FragmentScope
import com.example.catbook.ui.CatHomeFragment
import com.example.catbook.ui.CatImageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeActivityFragmentsBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [CatHomeFragmentModule::class])
    abstract fun contributesCatHomeFragments(): CatHomeFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [CatImageFragmentModule::class])
    abstract fun contributesCatImageFragment(): CatImageFragment
}