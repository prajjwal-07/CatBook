package com.example.catbook.di.module

import androidx.lifecycle.ViewModel
import com.example.catbook.di.ViewModelKey
import com.example.catbook.viewModel.CatHomeViewModel
import com.example.catbook.viewModel.CatImageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CatHomeViewModel::class)
    internal abstract fun bindsCatHomeViewModel(catHomeViewModel: CatHomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CatImageViewModel::class)
    internal abstract fun bindsCatImageViewModel(catImageViewModel: CatImageViewModel): ViewModel
}