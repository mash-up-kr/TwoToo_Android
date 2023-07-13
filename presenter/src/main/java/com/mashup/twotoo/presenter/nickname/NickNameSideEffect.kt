package com.mashup.twotoo.presenter.nickname

import model.user.UserInfoDomainModel

sealed class NickNameSideEffect {
    object NavigateToSendInvitation : NickNameSideEffect()
    object NavigateToHome : NickNameSideEffect()
}
