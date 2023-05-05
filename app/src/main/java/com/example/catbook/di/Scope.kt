package com.example.catbook.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import javax.inject.Scope
import kotlin.reflect.KClass

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class FragmentScope

@MustBeDocumented
@Retention
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)