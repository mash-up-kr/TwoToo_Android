package com.mashup.twotoo.presenter.home.di

import com.mashup.twotoo.presenter.home.HomeViewModel
import dagger.Subcomponent

/**
 * @Created by 김현국 2023/06/27
 */

@Subcomponent(modules = [HomeModule::class])
@HomeScope
interface HomeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun getViewModel(): HomeViewModel
}

interface HomeComponentProvider {

    fun provideHomeComponent(): HomeComponent
}
