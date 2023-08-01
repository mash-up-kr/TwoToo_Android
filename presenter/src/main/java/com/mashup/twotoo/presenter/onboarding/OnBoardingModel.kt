package com.mashup.twotoo.presenter.onboarding

data class OnBoardingModel(
    val loadingIndicatorState: Boolean = false,
    val isSuccessLogin: Boolean = false,
    val socialId: String = "",
    val deviceToken: String = "",
)
