package com.mashup.twotoo.presenter.invite

sealed class InviteSideEffect {
    data class Toast(val toastMessage: String) : InviteSideEffect()
    object NavigateToWaitingPair : InviteSideEffect()
    object NavigateToHome : InviteSideEffect()
    object SendSharedInvitation : InviteSideEffect()
    object MatchingPartner : InviteSideEffect()
}
