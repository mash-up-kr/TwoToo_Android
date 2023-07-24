package com.mashup.twotoo.presenter.splash.di

import com.mashup.twotoo.presenter.splash.SplashViewModel
import dagger.Subcomponent

@Subcomponent(modules = [SplashModule::class])
@SplashScope
interface SplashComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SplashComponent
    }

    fun getViewModel(): SplashViewModel
}

interface SplashComponentProvider {
    fun provideSplashComponent(): SplashComponent
}
