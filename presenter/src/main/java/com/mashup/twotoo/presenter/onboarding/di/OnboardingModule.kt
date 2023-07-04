package com.mashup.twotoo.presenter.onboarding.di

import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.onboarding.OnBoardingViewModel
import dagger.Binds
import dagger.Module
import javax.inject.Scope

@Module
abstract class OnboardingModule {

    @Binds
    @OnboardingScope
    abstract fun provideViewModel(onBoardingViewModel: OnBoardingViewModel): ViewModel
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class OnboardingScope
