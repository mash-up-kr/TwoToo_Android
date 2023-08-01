package com.mashup.twotoo.presenter.home.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.HomeScreen
import com.mashup.twotoo.presenter.home.model.HomeStateUiModel
import com.mashup.twotoo.presenter.home.preview.parameter.PreviewCheerHomeParameterProvider
import com.mashup.twotoo.presenter.navigation.TopLevelDestination
import com.mashup.twotoo.presenter.twotoo.TwoTooBottomBar
import com.mashup.twotoo.presenter.util.DevicePreview

@DevicePreview
@Composable
fun PreviewHomeScreenCheerChallenge(
    @PreviewParameter(PreviewCheerHomeParameterProvider::class) homeStateUiModel: HomeStateUiModel,
) {
    TwoTooTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                TwoTooBottomBar(
                    destinations = TopLevelDestination.values().asList(),
                    onNavigateToDestination = {},
                    currentDestination = null,
                    containerColor = TwoTooTheme.color.mainPink,
                    unSelectedColor = TwoTooTheme.color.backgroundYellow,
                )
            },
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                HomeScreen(
                    modifier = Modifier.fillMaxSize(),
                    state = homeStateUiModel,
                    navigateToGuide = {},
                )
            }
        }
    }
}
