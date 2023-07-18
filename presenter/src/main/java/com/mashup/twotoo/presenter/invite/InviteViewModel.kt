package com.mashup.twotoo.presenter.invite

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.twotoo.presenter.constant.TAG
import com.mashup.twotoo.presenter.util.createInviteDeepLink
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import usecase.user.GetPartnerInfoUseCase
import usecase.user.GetUserInfoUseCase
import javax.inject.Inject

class InviteViewModel @Inject constructor(
    private val getPartnerInfoUseCase: GetPartnerInfoUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel(), ContainerHost<InviteState, InviteSideEffect> {
    override val container = container<InviteState, InviteSideEffect>(InviteState())

    /**
     * get UserInfo
     */
    fun getUserInfo() = intent {
        getUserInfoUseCase.invoke().onSuccess { userInfo ->
            reduce {
                state.copy(userNo = userInfo.userNo, userNickName = userInfo.nickname)
            }
        }.onFailure {
        }
    }

    /**
     * refresh -> matching user
     */
    fun getPartnerInfo() {
        viewModelScope.launch {
            getPartnerInfoUseCase.invoke().onSuccess { partnerInfo ->
                if (partnerInfo.partnerNo != null) {
                    navigateToHome()
                } else {
                    toastMessage("상대방 아직 수락을 하지 않았어요!")
                }
            }.onFailure {
            }
        }
    }

    fun createInviteCode(
        userNo: Int,
        nickname: String,
        onSuccessUri: (Intent?) -> Unit
    ) {
        Log.d(TAG, "createInviteCode: $userNo$nickname")
        createInviteDeepLink(userNo, nickname) { uri ->
            if (uri != null) {
                onSuccessUri(shareInviteUrl(uri))
            } else {
                toastMessage("")
            }
        }
    }

    private fun shareInviteUrl(inviteLink: Uri?): Intent? {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, inviteLink.toString())
            type = "text/plain"
        }
        return Intent.createChooser(sendIntent, "twotoo")
    }

    private fun toastMessage(message: String) = intent {
        postSideEffect(InviteSideEffect.Toast(message))
    }

    fun navigateToWaitingAcceptPair() = intent {
        postSideEffect(InviteSideEffect.NavigateToWaitingPair)
    }

    fun navigateToHome() = intent {
        postSideEffect(InviteSideEffect.NavigateToHome)
    }
}
