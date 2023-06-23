package com.mashup.twotoo.presenter.home

import com.mashup.twotoo.presenter.di.HomeScreenScope
import com.mashup.twotoo.presenter.di.ScreenComponent
import com.mashup.twotoo.presenter.di.ViewModelFactoryModule
import com.mashup.twotoo.presenter.di.ViewModelModule
import dagger.Component

/**
 * @Created by 김현국 2023/06/23
 */

@Component(
    modules = [
        ViewModelModule::class, ViewModelFactoryModule::class,
    ],
    dependencies = [ScreenComponent::class],
)
@HomeScreenScope
interface HomeComponent {

    @Component.Builder
    interface Builder {
        fun build(
            screenComponent: ScreenComponent,
        ): HomeComponent
    }

    fun getViewModel(): HomeViewModel

    interface HomeComponentProvider {
        fun provideHomeComponent(): HomeComponent
    }
}
