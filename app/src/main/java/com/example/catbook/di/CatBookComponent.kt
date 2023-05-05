package com.example.catbook.di

import android.app.Application
import com.example.catbook.CatBookApplication
import com.example.catbook.di.module.HomeActivityBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        HomeActivityBuilderModule::class,
        AndroidSupportInjectionModule::class,
    ]
)
interface CatBookComponent {

    @Component.Builder
    interface Builder {
        fun build(): CatBookComponent

        @BindsInstance
        fun application(application: Application): Builder
    }

    fun inject(catBookApplication: CatBookApplication)
}