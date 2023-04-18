package com.mashup.hongsam.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoroutineModule::class, NetworkModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(): ApplicationComponent
    }
}
