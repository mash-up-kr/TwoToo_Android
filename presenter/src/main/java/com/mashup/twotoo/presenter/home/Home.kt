package com.mashup.twotoo.presenter.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.TwoTooBottomSheet
import com.mashup.twotoo.presenter.designsystem.component.dialog.TwoTooDialog
import com.mashup.twotoo.presenter.designsystem.component.toast.SnackBarHost
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.before.HomeBeforeChallenge
import com.mashup.twotoo.presenter.home.model.BeforeChallengeState
import com.mashup.twotoo.presenter.home.model.BeforeChallengeUiModel
import com.mashup.twotoo.presenter.home.model.ChallengeStateTypeUiModel
import com.mashup.twotoo.presenter.home.model.HomeChallengeInfoModel
import com.mashup.twotoo.presenter.home.model.HomeGoalAchievePartnerAndMeUiModel
import com.mashup.twotoo.presenter.home.model.HomeGoalAchieveUiModel
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel
import com.mashup.twotoo.presenter.home.ongoing.HomeOngoingChallenge
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    navigateToHistory: (Int) -> Unit = {},
    navigateToCreateChallenge: (BeforeChallengeState, HomeChallengeInfoModel) -> Unit,
    navigateToGuide: () -> Unit,
) {
    val homeSideEffectHandler = rememberHomeSideEffectHandler(
        navigateToCreateChallenge = navigateToCreateChallenge,
        navigateToHistory = navigateToHistory,
        openCheerBottomSheet = homeViewModel::openToCheerBottomSheet,
        onClickCheerDialogNegativeButton = homeViewModel::onClickCheerDialogNegativeButton,
        onClickCompleteDialogConfirmButton = homeViewModel::onClickCompleteDialogConfirmButton,
        removeVisibilityCheerDialog = homeViewModel::removeVisibilityCheerDialogSideEffect,
        removeVisibilityCompleteDialog = homeViewModel::removeVisibilityCompleteDialogSideEffect,
        callViewHomeApi = homeViewModel::getHomeViewChallenge,
        setInvisibleCheerDialog = homeViewModel::setInvisibleCheerDialogSideEffect,
        setInvisibleCompleteDialog = homeViewModel::setInvisibleCompleteDialogSideEffect,
    )
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by homeViewModel.collectAsState()

    LaunchedEffect(Unit) {
        launch {
            lifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                homeViewModel.getHomeViewChallenge()
            }
        }
    }

    homeViewModel.collectSideEffect { sideEffect ->
        homeSideEffectHandler.handleSideEffect(sideEffect = sideEffect)
    }

    Box(
        modifier = modifier,
    ) {
        HomeScreen(
            state = state.challengeStateUiModel,
            modifier = modifier.testTag(stringResource(id = R.string.home)),
            onBeeButtonClick = homeViewModel::openToShotBottomSheet,
            onClickBeforeChallengeTextButton = homeViewModel::onClickBeforeChallengeTextButton,
            onCommit = homeViewModel::openToAuthBottomSheet,
            navigateToHistory = homeViewModel::navigateToHistory,
            onClickCompleteButton = homeViewModel::onClickCompleteButton,
            onClickCheerButton = homeViewModel::openToCheerBottomSheet,
            navigateToGuide = navigateToGuide,
            onWiggleAnimationEnd = homeViewModel::onWiggleAnimationEnd,
        )

        with(homeSideEffectHandler) {
            if (isBottomSheetVisible) {
                TwoTooBottomSheet(
                    type = bottomSheetType,
                    onDismiss = ::onDismiss,
                    onClickButton = homeViewModel::onClickSendBottomSheetDataButton,
                )
            }

            SnackBarHost(
                Modifier.align(Alignment.BottomCenter),
                snackbarHostState,
            )

            if (isHomeDialogVisible) {
                TwoTooDialog(
                    content = homeDialogType,
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToHistory: (Int) -> Unit = {},
    onBeeButtonClick: () -> Unit = {},
    onClickBeforeChallengeTextButton: (BeforeChallengeState) -> Unit = {},
    onCommit: () -> Unit = {},
    onClickCompleteButton: (Int) -> Unit = {},
    state: ChallengeStateTypeUiModel = OngoingChallengeUiModel.default,
    onClickCheerButton: () -> Unit = {},
    navigateToGuide: () -> Unit,
    onWiggleAnimationEnd: () -> Unit = {},
) {
    ConstraintLayout(
        modifier = modifier.semantics {
            testTagsAsResourceId = true
        },
    ) {
        val (toolbar, background, content) = createRefs()
        TwoTooMainToolbar(
            modifier = Modifier.constrainAs(toolbar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            onClickHelpIcon = {
                navigateToGuide()
            },
        )
        TwoTooImageView(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.33f).constrainAs(background) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            previewPlaceholder = R.drawable.image_home_background,
            model = R.drawable.image_home_background,
            contentScale = ContentScale.FillBounds,
        )
        when (state) {
            is BeforeChallengeUiModel -> {
                HomeBeforeChallenge(
                    modifier = Modifier.fillMaxSize().constrainAs(content) {
                        top.linkTo(toolbar.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                    beforeChallengeUiModel = state,
                    onClickBeforeChallengeTextButton = onClickBeforeChallengeTextButton,
                )
            }
            is OngoingChallengeUiModel -> {
                HomeOngoingChallenge(
                    navigateToHistory = navigateToHistory,
                    modifier = Modifier.fillMaxSize().constrainAs(content) {
                        top.linkTo(toolbar.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                    ongoingChallengeUiModel = state,
                    onBeeButtonClick = onBeeButtonClick,
                    onCommit = onCommit,
                    onCompleteButtonClick = onClickCompleteButton,
                    onClickCheerButton = onClickCheerButton,
                    onWiggleAnimationEnd = onWiggleAnimationEnd,
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
            navigateToGuide = {},
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
            navigateToGuide = {},
        )
    }
}


@Preview(showBackground = true, device = "id:pixel_2")
@Composable
fun PreviewHomeScreenAfterChallengeText() {
    TwoTooTheme {
        HomeScreen(
            modifier = Modifier.fillMaxSize(),
            state = OngoingChallengeUiModel.default.copy(
                homeGoalAchievePartnerAndMeUiModel = HomeGoalAchievePartnerAndMeUiModel.default.copy(
                    partner = HomeGoalAchieveUiModel.default.copy(
                        "주주주주"
                    ),
                    me = HomeGoalAchieveUiModel.default.copy(
                        "주주"
                    )
                )
            ),
            navigateToGuide = {},
        )
    }
}


@Preview(showBackground = true, device = "id:pixel_2")
@Composable
fun PreviewHomeScreenAfterChallengeText2() {
    TwoTooTheme {
        HomeScreen(
            modifier = Modifier.fillMaxSize(),
            state = OngoingChallengeUiModel.default.copy(
                homeGoalAchievePartnerAndMeUiModel = HomeGoalAchievePartnerAndMeUiModel.default.copy(
                    partner = HomeGoalAchieveUiModel.default.copy(
                        "주주"
                    ),
                    me = HomeGoalAchieveUiModel.default.copy(
                        "주주"
                    )
                )
            ),
            navigateToGuide = {},
        )
    }
}
