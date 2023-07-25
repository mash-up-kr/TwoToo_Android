package com.mashup.twotoo.presenter.splash.di

import com.mashup.twotoo.presenter.splash.SplashViewModel
import dagger.Module
import dagger.Provides
import usecase.user.GetIsSendInvitationUseCase
import usecase.user.GetPreferenceUserInfoUseCase
import usecase.user.SetPreferenceUserInfoUseCase
import usecase.user.UserAuthUseCase
import javax.inject.Scope

@Module
class SplashModule {

    @Provides
    @SplashScope
    fun provideViewModel(
        getPreferenceUserInfoUseCase: GetPreferenceUserInfoUseCase,
        setPreferenceUserInfoUseCase: SetPreferenceUserInfoUseCase,
        userAuthUseCase: UserAuthUseCase,
        getIsSendInvitationUseCase: GetIsSendInvitationUseCase
    ): SplashViewModel {
        return SplashViewModel(
            getPreferenceUserInfoUseCase = getPreferenceUserInfoUseCase,
            setPreferenceUserInfoUseCase = setPreferenceUserInfoUseCase,
            userAuthUseCase = userAuthUseCase,
            getIsSendInvitationUseCase = getIsSendInvitationUseCase,
        )
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class SplashScope
