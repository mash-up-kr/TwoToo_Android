package com.mashup.twotoo.presenter.splash

sealed class SplashSideEffect {
    object NavigateToOnboarding : SplashSideEffect()
    object NavigateToHome : SplashSideEffect()
    object NavigateToNickNameSetting : SplashSideEffect()
    object NavigateToWaitingPair : SplashSideEffect()
    object NavigateToSendInvitation : SplashSideEffect()
}
