package com.mashup.twotoo.presenter.garden.di

import com.mashup.twotoo.presenter.garden.GardenViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Module
class GardenModule {
    @Provides
    @GardenScope
    fun provideViewModel(): GardenViewModel {
        return GardenViewModel()
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class GardenScope
