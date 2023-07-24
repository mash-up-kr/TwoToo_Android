package com.mashup.twotoo.presenter.onboarding.di

import com.mashup.twotoo.presenter.onboarding.OnBoardingViewModel
import dagger.Module
import dagger.Provides
import usecase.user.SetPreferenceUserInfoUseCase
import usecase.user.UserAuthUseCase
import javax.inject.Scope

@Module
class OnboardingModule {

    @Provides
    @OnboardingScope
    fun provideViewModel(
        setPreferenceUserInfoUseCase: SetPreferenceUserInfoUseCase,
        userAuthUseCase: UserAuthUseCase
    ): OnBoardingViewModel {
        return OnBoardingViewModel(
            setPreferenceUserInfoUseCase = setPreferenceUserInfoUseCase,
            userAuthUseCase = userAuthUseCase,
        )
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class OnboardingScope
