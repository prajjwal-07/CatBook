package com.example.catbook.di.module

import android.content.Context
import com.example.catbook.ui.HomeActivity
import com.example.catbook.di.ActivityScope
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        HomeActivityFragmentsBuilderModule::class,
    ]
)
class HomeActivityModule {

    @ActivityScope
    @Provides
    fun provideContext(homeActivity: HomeActivity): Context {
        return homeActivity
    }
}