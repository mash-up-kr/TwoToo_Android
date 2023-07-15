package com.mashup.twotoo.presenter.onboarding

data class OnBoardingModel(
    val isSuccessLogin: Boolean = false,
    val socialId: String = "",
    val deviceToken: String = "",
)
