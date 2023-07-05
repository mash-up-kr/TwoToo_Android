package com.mashup.twotoo.presenter.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.before.HomeBeforeChallenge
import com.mashup.twotoo.presenter.home.model.BeforeChallengeState
import com.mashup.twotoo.presenter.home.model.BeforeChallengeUiModel
import com.mashup.twotoo.presenter.home.model.ChallengeStateTypeUiModel
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel
import com.mashup.twotoo.presenter.home.ongoing.HomeOngoingChallenge

@Composable
fun HomeRoute(
    state: ChallengeStateTypeUiModel,
    modifier: Modifier = Modifier,
    onBeeButtonClick: () -> Unit = {},
    navigateToHistory: () -> Unit = {},
    onClickBeforeChallengeTextButton: (BeforeChallengeState) -> Unit = {},
) {
    HomeScreen(
        navigateToHistory = navigateToHistory,
        state = state,
        modifier = modifier.testTag(stringResource(id = R.string.home)),
        onBeeButtonClick = onBeeButtonClick,
        onClickBeforeChallengeTextButton = onClickBeforeChallengeTextButton,
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToHistory: () -> Unit = {},
    onBeeButtonClick: () -> Unit = {},
    onClickBeforeChallengeTextButton: (BeforeChallengeState) -> Unit = {},
    state: ChallengeStateTypeUiModel = OngoingChallengeUiModel.default,
) {
    ConstraintLayout(modifier = modifier) {
        val (topBar, homeBeforeChallenge, homeOngoingChallenge) = createRefs()
        TwoTooMainToolbar(
            modifier = Modifier.constrainAs(topBar) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            },
            onClickHelpIcon = {
            },
        )
        when (state) {
            is BeforeChallengeUiModel -> {
                HomeBeforeChallenge(
                    modifier = Modifier.constrainAs(homeBeforeChallenge) {
                        top.linkTo(topBar.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                    },
                    beforeChallengeUiModel = state,
                    onClickBeforeChallengeTextButton = onClickBeforeChallengeTextButton,
                )
            }
            is OngoingChallengeUiModel -> {
                HomeOngoingChallenge(
                    navigateToHistory = navigateToHistory,
                    modifier = Modifier.constrainAs(homeOngoingChallenge) {
                        top.linkTo(topBar.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                    },
                    ongoingChallengeUiModel = state,
                    onBeeButtonClick = onBeeButtonClick,
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
            state = OngoingChallengeUiModel.default,
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_2")
@Composable
fun PreviewHomeScreenAfterChallenge() {
    TwoTooTheme {
        HomeScreen(
            modifier = Modifier.fillMaxSize(),
            state = OngoingChallengeUiModel.default,
        )
    }
}
