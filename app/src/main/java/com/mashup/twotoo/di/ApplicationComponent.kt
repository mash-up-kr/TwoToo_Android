package com.mashup.twotoo.di

import android.content.Context
import com.mashup.twotoo.presenter.di.ScreenModule
import com.mashup.twotoo.presenter.garden.di.GardenComponent
import com.mashup.twotoo.presenter.history.di.HistoryComponent
import com.mashup.twotoo.presenter.home.di.HomeComponent
import com.mashup.twotoo.presenter.invite.di.InviteComponent
import com.mashup.twotoo.presenter.mypage.di.UserComponent
import com.mashup.twotoo.presenter.nickname.di.NickNameSettingComponent
import com.mashup.twotoo.presenter.onboarding.di.OnboardingComponent
import com.mashup.twotoo.presenter.splash.di.SplashComponent
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
        ApiModule::class,
        ScreenModule::class,
    ],
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun splashComponent(): SplashComponent.Factory
    fun homeComponent(): HomeComponent.Factory
    fun userComponent(): UserComponent.Factory
    fun onBoardingComponent(): OnboardingComponent.Factory
    fun nickNameSettingComponent(): NickNameSettingComponent.Factory
    fun inviteComponent(): InviteComponent.Factory
    fun historyComponent(): HistoryComponent.Factory
    fun gardenComponent(): GardenComponent.Factory
}
