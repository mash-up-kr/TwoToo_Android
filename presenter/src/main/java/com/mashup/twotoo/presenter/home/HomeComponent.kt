package com.mashup.twotoo.presenter.home

import com.mashup.twotoo.presenter.di.HomeScreenScope
import com.mashup.twotoo.presenter.di.viewModel.ViewModelFactoryModule
import com.mashup.twotoo.presenter.di.viewModel.ViewModelModule
import dagger.Subcomponent

/**
 * @Created by 김현국 2023/06/23
 */

@Subcomponent(
    modules = [
        ViewModelModule::class, ViewModelFactoryModule::class,
    ],
)
@HomeScreenScope
interface HomeComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): HomeComponent
    }

    fun getViewModel(): HomeViewModel
}
