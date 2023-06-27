package com.mashup.twotoo.presenter.invite

import com.mashup.twotoo.presenter.invite.model.InviteType

data class InviteState(
    val status: InviteType = InviteType.Send,
    val refreshClick: Boolean = false,
    val sendInvitation: Boolean = false
)
