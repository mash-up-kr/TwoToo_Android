package com.mashup.twotoo.di

import android.content.Context
import com.mashup.twotoo.presenter.di.ScreenComponent
import com.mashup.twotoo.presenter.home.HomeComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoroutineModule::class,
        NetworkModule::class,
        DataStoreModule::class,
        RepositoryModule::class,
        HomeComponent::class,
    ],
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    interface ScreenComponentProvider {
        fun provideScreenComponent(): ScreenComponent
    }
}
