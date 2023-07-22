package com.mashup.twotoo.presenter.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

private const val SplashWaitTime: Long = 1500

@Composable
fun SplashRoute(
    splashViewModel: SplashViewModel,
    onStateChangeSuccess: (String) -> Unit,
) {
    val state by splashViewModel.collectAsState()

    LaunchedEffect(key1 = state) {
        splashViewModel.getUserInfo()
    }

    splashViewModel.collectSideEffect { sideEffect ->
        delay(SplashWaitTime)
        when (sideEffect) {
            SplashSideEffect.NavigateToOnboarding -> {
                onStateChangeSuccess(NavigationRoute.OnBoardingGraph.OnboardingScreen.route)
            }
            SplashSideEffect.NavigateToNickNameSetting -> {
                onStateChangeSuccess(NavigationRoute.NickNameSettingGraph.NickNameSettingScreen.route)
            }
            SplashSideEffect.NavigateToSendInvitation -> {
                onStateChangeSuccess(NavigationRoute.InvitationGraph.SendInvitationScreen.route)
            }
            SplashSideEffect.NavigateToWaitingPair -> {
                onStateChangeSuccess(NavigationRoute.InvitationGraph.WaitingAcceptPairScreen.route)
            }
            SplashSideEffect.NavigateToHome -> {
                onStateChangeSuccess(NavigationRoute.HomeGraph.HomeScreen.route)
            }
        }
    }
    SplashScreen()
}

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
        ) {
            TwoTooImageView(
                model = R.drawable.example_flower,
                modifier = Modifier.size(50.dp).align(Alignment.CenterHorizontally),
                previewPlaceholder = R.drawable.example_flower,
                contentScale = ContentScale.FillBounds,
            )
            Text(
                text = stringResource(id = R.string.app_name),
                textAlign = TextAlign.Center,
                style = TwoTooTheme.typography.headLineNormal28,
                color = TwoTooTheme.color.twoTooPink,
            )
        }
    }
}

@Preview
@Composable
fun PreviewSplash() {
    SplashScreen()
}
