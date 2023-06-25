package com.mashup.twotoo.presenter.di

import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @HomeScreenScope
    @ViewModelKey(HomeViewModel::class)
    abstract fun provideHomeViewModel(model: HomeViewModel): ViewModel
}
