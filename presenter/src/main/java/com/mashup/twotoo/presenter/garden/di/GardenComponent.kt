package com.mashup.twotoo.presenter.garden.di

import com.mashup.twotoo.presenter.garden.GardenViewModel
import dagger.Subcomponent

@Subcomponent(modules = [GardenModule::class])
@GardenScope
interface GardenComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): GardenComponent
    }

    fun getViewModel(): GardenViewModel
}

interface GardenComponentProvider {
    fun provideGardenComponent(): GardenComponent
}
