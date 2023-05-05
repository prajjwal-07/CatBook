package com.example.catbook.di.module

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import com.example.catbook.di.AppViewModelFactory
import com.example.catbook.di.FragmentScope
import com.example.catbook.epoxy.controller.CatImageController
import com.example.catbook.ui.CatImageFragment
import com.example.catbook.viewModel.CatImageViewModel
import dagger.Module
import dagger.Provides

@Module
class CatImageFragmentModule {

    @FragmentScope
    @Provides
    fun provideContext(catImageFragment: CatImageFragment): Activity {
        return catImageFragment.requireActivity()
    }

    @FragmentScope
    @Provides
    fun provideCatImageViewModel(
        fragment: CatImageFragment,
        factory: AppViewModelFactory
    ): CatImageViewModel {
        return ViewModelProvider(fragment, factory).get(CatImageViewModel::class.java)
    }

    @Provides
    @FragmentScope
    fun providesCatImageController(): CatImageController {
        return CatImageController()
    }
}