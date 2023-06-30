package com.mashup.twotoo.presenter.home.di

import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.home.HomeViewModel
import dagger.Binds
import dagger.Module
import javax.inject.Scope

/**
 * @Created by 김현국 2023/06/27
 */

@Module
abstract class HomeModule {

    // here is the simple code, but suppose that we provide something important here
    @Binds
    @HomeScope
    abstract fun provideViewModel(homeViewModel: HomeViewModel): ViewModel
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class HomeScope
