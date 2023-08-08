package com.mashup.twotoo.presenter.mypage

sealed class UserSideEffect {
    data class NavigateToRoute(val route: String) : UserSideEffect()
    object OpenSignOutConfirmDialog : UserSideEffect()
    object OpenSignOutSuccessDialog : UserSideEffect()
    object OpenDeletePartnerConfirmDialog : UserSideEffect()
}

enum class MyPageDialogType {
    SignOutConfirm, SignOutSuccess, DeletePartnerConfirm
}
