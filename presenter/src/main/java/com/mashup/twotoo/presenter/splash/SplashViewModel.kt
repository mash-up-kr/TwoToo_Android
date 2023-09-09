package com.mashup.twotoo.presenter.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.constant.TAG
import com.mashup.twotoo.presenter.onboarding.model.OnboardingState
import model.user.UserAuthRequestDomainModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import usecase.user.GetIsSendInvitationUseCase
import usecase.user.GetPreferenceUserInfoUseCase
import usecase.user.SetPreferenceUserInfoUseCase
import usecase.user.UserAuthUseCase
import util.onError
import util.onSuccess
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val getPreferenceUserInfoUseCase: GetPreferenceUserInfoUseCase,
    private val setPreferenceUserInfoUseCase: SetPreferenceUserInfoUseCase,
    private val userAuthUseCase: UserAuthUseCase,
    private val getIsSendInvitationUseCase: GetIsSendInvitationUseCase
) : ViewModel(), ContainerHost<SplashModel, SplashSideEffect> {
    override val container = container<SplashModel, SplashSideEffect>(SplashModel())

    fun getUserInfo() = intent {
        val userInfo = getPreferenceUserInfoUseCase()
        Log.d(TAG, "userinfo$userInfo")

        if (userInfo != null) {
            if (userInfo.accessToken.isNotEmpty() && userInfo.deviceToken.isNotEmpty() && userInfo.socialId!!.isNotEmpty()) {
                getUserAuth(
                    UserAuthRequestDomainModel(
                        socialId = userInfo.socialId!!,
                        deviceToken = userInfo.deviceToken,
                    ),
                )
            }
        } else {
            postSideEffect(SplashSideEffect.NavigateToOnboarding)
        }
    }

    private fun getUserAuth(userAuthRequestDomainModel: UserAuthRequestDomainModel) = intent {
        userAuthUseCase(userAuthRequestDomainModel).onSuccess { userInfo ->
            setPreferenceUserInfoUseCase(userInfo)
            reduce {
                state.copy(userInfo = userInfo)
            }
            when (userInfo.state) {
                OnboardingState.NEED_NICKNAME.name -> {
                    postSideEffect(SplashSideEffect.NavigateToNickNameSetting)
                }
                OnboardingState.NEED_MATCHING.name -> {
                    if (getIsSendInvitationUseCase()) {
                        postSideEffect(SplashSideEffect.NavigateToWaitingPair)
                    } else {
                        postSideEffect(SplashSideEffect.NavigateToSendInvitation)
                    }
                }
                OnboardingState.HOME.name -> {
                    postSideEffect(SplashSideEffect.NavigateToHome)
                }
            }
        }.onError { code, message ->
            postSideEffect(SplashSideEffect.NavigateToOnboarding)
        }
    }
}
