package com.mashup.twotoo.presenter.onboarding

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.width
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
import com.mashup.twotoo.presenter.designsystem.component.loading.FlowerLoadingIndicator
import com.mashup.twotoo.presenter.designsystem.component.scrim.TwoTooScrim
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.sdk.theme.KakaoLoginButtonTheme
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun OnBoardingRoute(
    onBoardingViewModel: OnBoardingViewModel,
    onLoginSuccess: (String) -> Unit = {},
) {
    val state by onBoardingViewModel.collectAsState()
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        OnBoardingScreen {
            onBoardingViewModel.loginWithKakao(context)
        }
        if (state.loadingIndicatorState) {
            Box(modifier = Modifier.fillMaxSize()) {
                TwoTooScrim(
                    color = TwoTooTheme.color.gray600,
                )
                FlowerLoadingIndicator(
                    modifier = Modifier.width(128.dp).height(144.dp).align(Alignment.Center),
                )
            }
        }
    }

    if (state.isSuccessLogin) {
        Log.d(TAG, "OnBoardingRoute: isLoginSuccess")
        onBoardingViewModel.signUpWithKakaoAccount(state.deviceToken, state.socialId)
    }

    onBoardingViewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is OnboardingSideEffect.NavigateToHome -> {
                onLoginSuccess(NavigationRoute.HomeGraph.HomeScreen.route)
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
            .fillMaxSize().navigationBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        TwoTooToolbar.TitleToolbar(modifier = Modifier.fillMaxWidth())
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

@Preview(showBackground = true)
@Composable
private fun OnBoardingPagerPreview() {
    OnBoardingScreen({})
}
