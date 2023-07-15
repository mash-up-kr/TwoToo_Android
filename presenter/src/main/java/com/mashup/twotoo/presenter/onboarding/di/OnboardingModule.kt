package com.mashup.twotoo.presenter.onboarding.di

import com.mashup.twotoo.presenter.onboarding.OnBoardingViewModel
import dagger.Module
import dagger.Provides
import usecase.token.SetAccessTokenUseCase
import usecase.user.SetUserNoUseCase
import usecase.user.UserAuthUseCase
import javax.inject.Scope

@Module
class OnboardingModule {

    @Provides
    @OnboardingScope
    fun provideViewModel(setAccessTokenUseCase: SetAccessTokenUseCase,
                         setUserNoUseCase: SetUserNoUseCase,
                         userAuthUseCase: UserAuthUseCase): OnBoardingViewModel {
        return OnBoardingViewModel(
            setAccessTokenUseCase = setAccessTokenUseCase,
            setUserNoUseCase = setUserNoUseCase,
            userAuthUseCase = userAuthUseCase,
        )
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class OnboardingScope
