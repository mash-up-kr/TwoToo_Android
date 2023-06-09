package com.mashup.twotoo.presenter.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooIconButtonImpl
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.sdk.theme.KakaoLoginButtonTheme

@Composable
fun OnBoardingRoute(
    onClickLoginButton: () -> Unit = {}
) {
    OnBoardingScreen(onClickLoginButton)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    onClickLoginButton: () -> Unit = {}
) {
    val pagerState = rememberPagerState()
    Scaffold(
        topBar = { TwoTooMainToolbar() },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .paint(
                    painterResource(id = R.drawable.image_background),
                    contentScale = ContentScale.FillBounds,
                ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                HorizontalPagerContent(pagerState = pagerState)

                Spacer(modifier = Modifier.weight(1f))
                if (pagerState.currentPage == MAX_COUNT - 1) {
                    KakaoLoginButton(onClickLoginButton)
                    Spacer(modifier = Modifier.height(56.dp))
                }
            }
        }
    }
}

@Composable
fun KakaoLoginButton(
    onClickLoginButton: () -> Unit
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
        onClickLoginButton()
    }
}

@Preview
@Composable
private fun OnBoardingPagerPreview() {
    OnBoardingScreen()
}
