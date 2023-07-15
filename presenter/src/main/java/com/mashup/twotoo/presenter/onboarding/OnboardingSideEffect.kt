package com.mashup.twotoo.presenter.onboarding

sealed class OnboardingSideEffect {
    object NavigateToHome : OnboardingSideEffect()
    object NavigateToNickNameSetting : OnboardingSideEffect()
    object NavigateToMatching : OnboardingSideEffect()
}
