package com.mashup.twotoo.di

import com.mashup.twotoo.presenter.MainActivity
import com.mashup.twotoo.presenter.di.ScreenComponent
import com.mashup.twotoo.presenter.di.ViewModelFactoryModule
import com.mashup.twotoo.presenter.di.ViewModelModule
import com.mashup.twotoo.presenter.home.HomeComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoroutineModule::class,
        NetworkModule::class,
        DataStoreModule::class,
        RepositoryModule::class,
        ScreenComponent::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
    ],
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(): ApplicationComponent
    }
    fun inject(mainActivity: MainActivity)
    fun homeComponent(): HomeComponent.Factory
}
