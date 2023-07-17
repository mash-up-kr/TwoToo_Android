package com.mashup.twotoo.presenter.nickname

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import model.user.UserNickNameDomainModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import usecase.user.SetNickNameUseCase
import javax.inject.Inject

class NickNameViewModel @Inject constructor(
    private val setNickNameUseCase: SetNickNameUseCase
) : ViewModel(), ContainerHost<NickNameState, NickNameSideEffect> {
    override val container = container<NickNameState, NickNameSideEffect>(NickNameState())

    fun setUserNickName(userNickName: String) {
        viewModelScope.launch {
            setNickNameUseCase.invoke(UserNickNameDomainModel(nickname = userNickName)).onSuccess { userInfo ->
                if (userInfo.partnerNo != null) {
                    navigateToWaitingPair()
                } else {
                    navigateToWaitingPair()
                }
            }.onFailure {
            }
        }
    }

    private fun navigateToWaitingPair() = intent {
        postSideEffect(NickNameSideEffect.NavigateToSendInvitation)
    }

    private fun navigateToHome() = intent {
        postSideEffect(NickNameSideEffect.NavigateToHome)
    }
}
