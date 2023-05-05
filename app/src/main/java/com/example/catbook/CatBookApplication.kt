package com.example.catbook

import android.app.Application
import com.example.catbook.di.CatBookComponent
import com.example.catbook.di.DaggerCatBookComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class CatBookApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        val appComponent = initDagger()
        appComponent.inject(this)
    }

    private fun initDagger(): CatBookComponent {
        return DaggerCatBookComponent.builder().application(this).build()
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}