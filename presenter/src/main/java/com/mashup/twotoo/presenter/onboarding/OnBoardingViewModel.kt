package com.mashup.twotoo.presenter.onboarding

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class OnBoardingViewModel : ViewModel(), ContainerHost<OnBoardingState, Unit> {
    override val container = container<OnBoardingState, Unit>(OnBoardingState())

    fun setUserNickname(nickName: String) = intent {
        reduce {
            state.copy(userNickName = nickName)
        }
    }
}
