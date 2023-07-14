package com.mashup.twotoo.presenter.onboarding

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.mashup.twotoo.presenter.constant.TAG
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooIconButtonImpl
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.sdk.theme.KakaoLoginButtonTheme
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun OnBoardingRoute(
    onBoardingViewModel: OnBoardingViewModel,
    onLoginSuccess: (String) -> Unit = {}
) {
    val state by onBoardingViewModel.collectAsState()
    val context = LocalContext.current

    OnBoardingScreen {
        onBoardingViewModel.loginWithKakao(context)
    }

    if (state.isSuccessLogin) {
        Log.d(TAG, "OnBoardingRoute: isLoginSuccess")
        onBoardingViewModel.signUpWithKakaoAccount(state.deviceToken, state.socialId)
    }

    onBoardingViewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is OnboardingSideEffect.NavigateToHome -> {
                onLoginSuccess(NavigationRoute.HomeScreenGraph.HomeScreen.route)
            }
            is OnboardingSideEffect.NavigateToNickNameSetting -> {
                onLoginSuccess(NavigationRoute.NickNameSettingGraph.NickNameSettingScreen.route)
            }
            is OnboardingSideEffect.NavigateToMatching -> {
                onLoginSuccess(NavigationRoute.InvitationGraph.SendInvitationScreen.route)
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    onClickKakaoLogin: () -> Unit,
) {
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TwoTooMainToolbar()
        HorizontalPagerContent(pagerState = pagerState)

        Spacer(modifier = Modifier.weight(1f))
        if (pagerState.currentPage == MAX_COUNT - 1) {
            KakaoLoginButton(onClickKakaoLogin)
            Spacer(modifier = Modifier.height(56.dp))
        }
    }
}

@Composable
fun KakaoLoginButton(
    onClickKakaoLogin: () -> Unit,
) {
    TwoTooIconButtonImpl(
        text = {
            Text(
                stringResource(id = KakaoLoginButtonTheme.TextId),
                color = KakaoLoginButtonTheme.ContentColor,
                style = TwoTooTheme.typography.headLineNormal18,
            )
        },
        iconId = KakaoLoginButtonTheme.IconId,
        buttonColor = KakaoLoginButtonTheme.ContainerColor,
        buttonRadius = KakaoLoginButtonTheme.Radius,
    ) {
        onClickKakaoLogin()
    }
}

@Preview
@Composable
private fun OnBoardingPagerPreview() {
    OnBoardingScreen({})
}
