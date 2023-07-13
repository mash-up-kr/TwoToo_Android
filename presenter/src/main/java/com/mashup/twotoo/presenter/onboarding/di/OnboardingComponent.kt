package com.mashup.twotoo.presenter.onboarding.di

import com.mashup.twotoo.presenter.onboarding.OnBoardingViewModel
import dagger.Subcomponent

@Subcomponent(modules = [OnboardingModule::class])
@OnboardingScope
interface OnboardingComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): OnboardingComponent
    }

    fun getViewModel(): OnBoardingViewModel
}

interface OnboardingComponentProvider {
    fun provideOnboardingComponent(): OnboardingComponent
}
