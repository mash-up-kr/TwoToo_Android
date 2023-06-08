package com.mashup.twotoo.presenter.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooIconButton
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.Yello

@Composable
fun OnBoardingRoute() {
    OnBoardingScreen()
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen() {
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TwoTooTheme.color.backgroundYellow),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TwoTooMainToolbar()
        Spacer(modifier = Modifier.height(100.dp))

        HorizontalPagerContent(pagerState = pagerState)

        Spacer(modifier = Modifier.weight(1f))
        if (pagerState.currentPage == MAX_COUNT - 1) {
            KakaoLoginButton()
            Spacer(modifier = Modifier.height(56.dp))
        }
    }
}

@Composable
fun KakaoLoginButton() {
    TwoTooIconButton(
        stringResource(id = R.string.login_tite),
        Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 10.dp),
        R.drawable.kakaotalk,
        Yello,
    ) {}
}

@Preview
@Composable
private fun OnBoardingPagerPreview() {
    OnBoardingScreen()
}
