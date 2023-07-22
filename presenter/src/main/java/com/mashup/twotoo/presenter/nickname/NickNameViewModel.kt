package com.mashup.twotoo.presenter.nickname

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.constant.TAG
import model.user.UserNickNameDomainModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import usecase.user.SetNickNameUseCase
import javax.inject.Inject

class NickNameViewModel @Inject constructor(
    private val setNickNameUseCase: SetNickNameUseCase
) : ViewModel(), ContainerHost<NickNameState, NickNameSideEffect> {
    override val container = container<NickNameState, NickNameSideEffect>(NickNameState())

    fun setPartnerInfo(nickname: String, partnerNo: Int) = intent {
        reduce {
            state.copy(partnerNo = partnerNo, partnerNickName = nickname)
        }
    }

    fun setUserNickName(userNickName: String) = intent {
        setNickNameUseCase.invoke(
            UserNickNameDomainModel(
                nickname = userNickName,
                partnerNo = if (state.partnerNo == 0) {
                    null
                } else {
                    state.partnerNo
                },
            ),
        ).onSuccess { userInfo ->
            if (userInfo.partnerNo != null) {
                postSideEffect(NickNameSideEffect.NavigateToHome)
            } else {
                postSideEffect(NickNameSideEffect.NavigateToSendInvitation(userNickName))
            }
        }.onFailure { error ->
            error.message?.let { msg ->
                Log.d(TAG, "viewModel: ${error.message}")
                toastMessage(msg)
            }
        }
    }

    fun toastMessage(msg: String) = intent {
        postSideEffect(NickNameSideEffect.ToastMessage(msg))
    }
}
