package com.mashup.twotoo.presenter.di

import com.mashup.twotoo.presenter.MainActivity
import dagger.Subcomponent

/**
 * @Created by 김현국 2023/06/27
 */

@Subcomponent(modules = [ViewModelFactoryModule::class, ViewModelModule::class])
interface ViewModelFactoryComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewModelFactoryComponent
    }

    fun inject(mainActivity: MainActivity)
}

interface ViewModelFactoryComponentProvider {
    fun provideViewModelFactoryComponent(): ViewModelFactoryComponent
}
