package com.mashup.twotoo.presenter.util

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.constant.TAG
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
    if (error != null) {
        Log.e(TAG, "카카오계정으로 로그인 실패 $error")
    } else if (token != null) {
        Log.d(TAG, "카카오계정으로 로그인 성공 access token :  ${token.accessToken}")
        Log.d(TAG, "카카오계정으로 로그인 성공 refresh token : ${token.refreshToken}")
    }
}

fun login(context: Context) = with(UserApiClient.instance) {
    if (!isKakaoTalkLoginAvailable(context)) {
        loginWithKakaoAccount(context, callback = callback)
        return@with
    }

    loginWithKakaoTalk(context) { token, error ->
        if (error != null) {
            Log.e(TAG, context.getString(R.string.failureKakaoTalkLogin) + error)

            // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
            // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                return@loginWithKakaoTalk
            }

            // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
            loginWithKakaoAccount(context, callback = callback)
        } else if (token != null) {
            Log.d(TAG, context.getString(R.string.successKakaoTalkLogin) + " access Token : " + token.accessToken)
            Log.d(TAG, context.getString(R.string.successKakaoTalkLogin) + " refresh Token : " + token.refreshToken)
        }
    }
}

fun logout(context: Context) {
    UserApiClient.instance.logout { error ->
        if (error != null) {
            Log.e(TAG, context.getString(R.string.failureKakaoTalkLogout), error)
        } else {
            Log.i(TAG, context.getString(R.string.successKakaoTalkLogout))
        }
    }
}

fun leave(context: Context) {
    UserApiClient.instance.unlink { error ->
        if (error != null) {
            Log.e(TAG, context.getString(R.string.failureKakaoTalkUnlink), error)
        } else {
            Log.i(TAG, context.getString(R.string.successKakaoTalkUnlink))
        }
    }
}

fun isHasToken(context: Context) {
    if (!AuthApiClient.instance.hasToken()) {
        // 로그인 필요
        Log.e(TAG, "isHasToken: false")
        return
    }

    UserApiClient.instance.accessTokenInfo { accessTokenInfo, error ->
        if (error != null) {
            if (error is KakaoSdkError && error.isInvalidTokenError()) {
                // 로그인 필요
                Log.e(TAG, "isHasToken: inValidTokenError", error)
            } else {
                // 기타 에러
                Log.e(TAG, "isHasToken: error = ", error)
            }
        } else {
                /*
                token 유효성 체크 성공
                accessTokenInfo : 액세스 토큰이 만료된 경우 자동으로 갱신된 새로운 액세스 토큰 정보 반환.
                 */
        }
    }
}

/**
 * Application에서 초기화를 진행해야해서 Preview에서는 테스트가 불가능합니다.
 *
 * MainActivity에서 이 컴포저블을 넣고 테스트하세요.
 *
 * 다음을 테스트할 수 있습니다.
 *
 * - 로그인
 * - 로그아웃
 * - 회원탈퇴
 */
@Composable
fun TestLoginOutLeave() {
    TwoTooTheme {
        val context = LocalContext.current
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Button(
                onClick = {
                    login(context = context)
                },
            ) {
                Text("로그인 테스트")
            }
            Button(
                onClick = {
                    logout(context = context)
                },
            ) {
                Text("로그아웃 테스트")
            }
            Button(
                onClick = {
                    leave(context = context)
                },
            ) {
                Text("탈퇴 테스트")
            }
        }
    }
}
