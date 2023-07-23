package com.mashup.twotoo.presenter.createChallenge

sealed class CreateChallengeSideEffect {
    object NavigateToSelectFlower : CreateChallengeSideEffect()
    object NavigateToSuccessCreate : CreateChallengeSideEffect()
    object NavigateToHome : CreateChallengeSideEffect()
    data class ToastMessage(val message: String) : CreateChallengeSideEffect()
}
