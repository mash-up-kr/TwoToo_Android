package com.mashup.twotoo.presenter.util

import android.content.Context
import android.util.Log
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.mashup.twotoo.presenter.constant.TAG
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun UserApiClient.Companion.login(context: Context): Flow<LoginState> = callbackFlow {
    trySend(LoginState.Loading)
    if (instance.isKakaoTalkLoginAvailable(context)) {
        loginWithKakaoTalk(context)
    } else {
        loginWithKakaoAccount(context)
    }
    awaitClose {}
}

private fun ProducerScope<LoginState>.loginWithKakaoTalk(context: Context) =
    UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
        if (error != null) {
            checkLoginWithKakaoTalkError(context, error)
        } else if (token != null) {
            trySend(LoginState.Success(token))
            close()
        } else {
            trySend(LoginState.Error(IllegalStateException("Can't Receive Kaokao Access Token")))
            close()
        }
    }

private fun ProducerScope<LoginState>.checkLoginWithKakaoTalkError(
    context: Context,
    error: Throwable,
) {
    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
        trySend(LoginState.Error(error))
        close()
    } else {
        loginWithKakaoAccount(context)
    }
}

private fun ProducerScope<LoginState>.loginWithKakaoAccount(context: Context) =
    UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
        if (error != null) {
            trySend(LoginState.Error(error))
        } else if (token != null) {
            trySend(LoginState.Success(token))
        } else {
            trySend(LoginState.Error(IllegalStateException("Can't Receive Kaokao Access Token")))
        }
        close()
    }

fun getKakaoUserInfoFlow() = callbackFlow<String?> {
    UserApiClient.instance.me { user, error ->
        if (error != null) {
            Log.e(TAG, "사용자 정보 요청 실패", error)
            trySend(error.message.toString())
        } else if (user != null) {
            Log.i(
                TAG,
                "사용자 정보 요청 성공" +
                    "\n회원번호: ${user.id}" +
                    "\n이메일: ${user.kakaoAccount?.email}",
            )
            user.kakaoAccount?.email?.let { id ->
                trySend(id)
            }
        }
    }
    awaitClose()
}

sealed class LoginState {
    object Loading : LoginState()
    data class Success(val token: OAuthToken) : LoginState()
    data class Error(val throwable: Throwable?) : LoginState()
}
