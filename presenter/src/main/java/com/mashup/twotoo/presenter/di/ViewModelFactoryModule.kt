package com.mashup.twotoo.presenter.di

import androidx.lifecycle.ViewModelProvider
import com.mashup.twotoo.presenter.home.HomeComponent
import dagger.Binds
import dagger.Module

@Module(
    subcomponents = [HomeComponent::class],
)
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
