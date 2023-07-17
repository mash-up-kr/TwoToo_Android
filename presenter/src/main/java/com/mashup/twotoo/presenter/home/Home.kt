package com.mashup.twotoo.presenter.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.TwoTooBottomSheet
import com.mashup.twotoo.presenter.designsystem.component.dialog.TwoTooDialog
import com.mashup.twotoo.presenter.designsystem.component.toast.SnackBarHost
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.before.HomeBeforeChallenge
import com.mashup.twotoo.presenter.home.model.BeforeChallengeState
import com.mashup.twotoo.presenter.home.model.BeforeChallengeUiModel
import com.mashup.twotoo.presenter.home.model.ChallengeStateTypeUiModel
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel
import com.mashup.twotoo.presenter.home.ongoing.HomeOngoingChallenge
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    navigateToHistory: () -> Unit = {},
    navigateToCreateChallenge: () -> Unit = {},
) {
    val homeSideEffectHandler = rememberHomeSideEffectHandler(
        navigateToCreateChallenge = navigateToCreateChallenge,
        navigateToHistory = navigateToHistory,
        openCheerBottomSheet = homeViewModel::openToCheerBottomSheet,
        setVisibilityCheerDialog = homeViewModel::onClickCheerDialogNegativeButton,
        setVisibilityCompleteDialog = homeViewModel::onClickCompleteDialogConfirmButton,
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
        modifier = Modifier.fillMaxSize(),
    ) {
        HomeScreen(
            state = state,
            modifier = modifier.testTag(stringResource(id = R.string.home)),
            onBeeButtonClick = homeViewModel::openToShotBottomSheet,
            onClickBeforeChallengeTextButton = homeViewModel::onClickBeforeChallengeTextButton,
            onCommit = homeViewModel::openToAuthBottomSheet,
            navigateToHistory = homeViewModel::navigateToHistory,
            onClickCompleteButton = homeViewModel::onClickCompleteButton,
            onClickCheerButton = homeViewModel::openToCheerBottomSheet,
        )

        with(homeSideEffectHandler) {
            if (isBottomSheetVisible) {
                TwoTooBottomSheet(
                    bottomSheetState = bottomSheetState,
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
    navigateToHistory: () -> Unit = {},
    onBeeButtonClick: () -> Unit = {},
    onClickBeforeChallengeTextButton: (BeforeChallengeState) -> Unit = {},
    onCommit: () -> Unit = {},
    onClickCompleteButton: (Int) -> Unit = {},
    state: ChallengeStateTypeUiModel = OngoingChallengeUiModel.default,
    onClickCheerButton: () -> Unit = {},
) {
    ConstraintLayout(
        modifier = modifier.semantics {
            testTagsAsResourceId = true
        },
    ) {
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
                    onCommit = onCommit,
                    onCompleteButtonClick = onClickCompleteButton,
                    onClickCheerButton = onClickCheerButton,
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
