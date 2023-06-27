package com.mashup.twotoo.presenter.invite

sealed class InviteSideEffect {
    data class Toast(val toastMessage: String) : InviteSideEffect()
}
