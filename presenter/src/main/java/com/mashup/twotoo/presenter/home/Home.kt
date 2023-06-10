package com.mashup.twotoo.presenter.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.model.BeforeChallengeUiModel
import com.mashup.twotoo.presenter.home.model.ChallengeStateTypeUiModel
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel

@Composable
fun HomeRoute() {
    HomeScreen()
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    challengeStateTypeUiModel: ChallengeStateTypeUiModel = BeforeChallengeUiModel.empty,
) {
    Box(modifier = modifier) {
        when (challengeStateTypeUiModel) {
            is BeforeChallengeUiModel -> {
                HomeBeforeChallenge(
                    modifier = Modifier.fillMaxSize(),
                    beforeChallengeUiModel = challengeStateTypeUiModel,
                )
            }
            is OngoingChallengeUiModel -> {
                HomeOngoingChallenge(
                    modifier = Modifier.fillMaxSize(),
                    ongoingChallengeUiModel = challengeStateTypeUiModel,
                    onBeeButtonClick = { /*TODO*/ },
                )
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_2")
@Composable
fun PreviewHomeScreenBeforeChallenge() {
    TwoTooTheme {
        HomeScreen(
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_2")
@Composable
fun PreviewHomeScreenAfterChallenge() {
    TwoTooTheme {
        HomeScreen(
            modifier = Modifier.fillMaxSize(),
            challengeStateTypeUiModel = OngoingChallengeUiModel.default,
        )
    }
}
