package com.example.catbook.di.module

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import com.example.catbook.di.AppViewModelFactory
import com.example.catbook.di.FragmentScope
import com.example.catbook.epoxy.controller.CatBreedController
import com.example.catbook.interfaces.CatHomeListener
import com.example.catbook.ui.CatHomeFragment
import com.example.catbook.viewModel.CatHomeViewModel
import dagger.Module
import dagger.Provides

@Module
class CatHomeFragmentModule {

    @FragmentScope
    @Provides
    fun provideContext(catHomeFragment: CatHomeFragment): Activity {
        return catHomeFragment.requireActivity()
    }

    @Provides
    @FragmentScope
    fun providesCatHomeListener(fragment: CatHomeFragment): CatHomeListener =
        fragment

    @FragmentScope
    @Provides
    fun provideCatHomeViewModel(
        fragment: CatHomeFragment,
        factory: AppViewModelFactory
    ): CatHomeViewModel {
        return ViewModelProvider(fragment, factory).get(CatHomeViewModel::class.java)
    }

    @Provides
    @FragmentScope
    fun providesCatBreedController(listener: CatHomeListener,): CatBreedController {
        return CatBreedController(listener)
    }

}