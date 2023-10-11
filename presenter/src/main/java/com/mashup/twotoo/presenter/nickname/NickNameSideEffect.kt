package com.mashup.twotoo.presenter.nickname

sealed class NickNameSideEffect {
    data class NavigateToSendInvitation(val nickname: String) : NickNameSideEffect()
    data class ToastMessage(val msg: String) : NickNameSideEffect()
    object NavigateToHome : NickNameSideEffect()
    object NavigateToMyPage : NickNameSideEffect()
}
