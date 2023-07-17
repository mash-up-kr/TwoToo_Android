package com.mashup.twotoo.presenter.invite

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import usecase.user.GetUserNoUseCase

class InviteViewModel(
    private val getUserNoUseCase: GetUserNoUseCase,
) : ViewModel(), ContainerHost<InviteState, InviteSideEffect> {
    override val container = container<InviteState, InviteSideEffect>(InviteState())

    fun sendInvitation() = intent {
    }

//    fun getUserNo() {
//    }
}
