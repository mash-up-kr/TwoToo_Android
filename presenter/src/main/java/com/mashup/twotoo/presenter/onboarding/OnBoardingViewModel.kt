package com.mashup.twotoo.presenter.onboarding

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.user.UserApiClient
import com.mashup.twotoo.presenter.constant.TAG
import com.mashup.twotoo.presenter.onboarding.model.OnboardingState
import com.mashup.twotoo.presenter.util.Logging.getFcmTokenFlow
import com.mashup.twotoo.presenter.util.LoginState
import com.mashup.twotoo.presenter.util.getKakaoUserInfoFlow
import com.mashup.twotoo.presenter.util.login
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import model.user.UserAuthRequestDomainModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import usecase.user.SetPreferenceUserInfoUseCase
import usecase.user.UserAuthUseCase
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor(
    private val setPreferenceUserInfoUseCase: SetPreferenceUserInfoUseCase,
    private val userAuthUseCase: UserAuthUseCase
) : ViewModel(), ContainerHost<OnBoardingModel, OnboardingSideEffect> {
    override val container = container<OnBoardingModel, OnboardingSideEffect>(OnBoardingModel())

    fun loginWithKakao(context: Context) = intent {
        viewModelScope.launch {
            val kakaoLogin = UserApiClient.login(context)
            val fcmToken = getFcmTokenFlow()
            val userInfo = getKakaoUserInfoFlow()
            combine(kakaoLogin, fcmToken, userInfo) { loginState, deviceToken, socialId ->
                reduce {
                    when (loginState) {
                        is LoginState.Success -> {
                            state.copy(isSuccessLogin = true, deviceToken = deviceToken, socialId = socialId ?: "")
                        }
                        is LoginState.Error -> {
                            state.copy(isSuccessLogin = false, deviceToken = deviceToken, socialId = socialId ?: "")
                        }
                        is LoginState.Loading -> {
                            state.copy(isSuccessLogin = false, deviceToken = deviceToken, socialId = socialId ?: "")
                        }
                    }
                }
            }.collectLatest { }
        }
    }

    fun signUpWithKakaoAccount(deviceToken: String, socialId: String) = intent {
        val userAuthModel = UserAuthRequestDomainModel(deviceToken = deviceToken, socialId = socialId)
        userAuthUseCase(userAuthModel).onSuccess { userInfo ->
            setPreferenceUserInfoUseCase(userInfo)
            when (userInfo.state) {
                OnboardingState.NEED_NICKNAME.name -> {
                    postSideEffect(OnboardingSideEffect.NavigateToNickNameSetting)
                }
                OnboardingState.NEED_MATCHING.name -> {
                    postSideEffect(OnboardingSideEffect.NavigateToMatching)
                }
                OnboardingState.HOME.name -> {
                    postSideEffect(OnboardingSideEffect.NavigateToHome)
                }
            }
        }.onFailure {
            Log.d(TAG, "failure: ${it.message}")
        }
    }
}
