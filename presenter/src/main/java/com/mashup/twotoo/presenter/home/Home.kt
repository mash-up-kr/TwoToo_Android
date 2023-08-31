package com.mashup.twotoo.presenter.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.TwoTooBottomSheet
import com.mashup.twotoo.presenter.designsystem.component.dialog.TwoTooDialog
import com.mashup.twotoo.presenter.designsystem.component.loading.FlowerLoadingIndicator
import com.mashup.twotoo.presenter.designsystem.component.toast.SnackBarHost
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.home.before.HomeBeforeChallenge
import com.mashup.twotoo.presenter.home.model.BeforeChallengeState
import com.mashup.twotoo.presenter.home.model.BeforeChallengeUiModel
import com.mashup.twotoo.presenter.home.model.HomeChallengeInfoModel
import com.mashup.twotoo.presenter.home.model.HomeGoalAchievePartnerAndMeUiModel
import com.mashup.twotoo.presenter.home.model.HomeStateUiModel
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel
import com.mashup.twotoo.presenter.home.ongoing.HomeOngoingChallenge
import com.mashup.twotoo.presenter.util.debounce
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    navigateToHistory: (Int, HomeGoalAchievePartnerAndMeUiModel?) -> Unit = { _, _ -> },
    navigateToCreateChallenge: (BeforeChallengeState, HomeChallengeInfoModel) -> Unit,
    navigateToGuide: () -> Unit = {},
    navigateToGarden: (Boolean) -> Unit = {},
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
        navigateToGarden = navigateToGarden,
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
            state = state,
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
                    onClickButton = debounce(300L, homeViewModel.viewModelScope, homeViewModel::onClickSendBottomSheetDataButton),
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
    state: HomeStateUiModel = HomeStateUiModel.init,
    onClickCheerButton: () -> Unit = {},
    navigateToGuide: () -> Unit,
    onWiggleAnimationEnd: () -> Unit = {},
) {
    Column(modifier = modifier) {
        TwoTooMainToolbar(
            modifier = Modifier.padding(top = 5.dp),
            onClickHelpIcon = {
                navigateToGuide()
            },
        )
        Crossfade(
            modifier = Modifier.fillMaxSize(),
            targetState = state.indicatorState,
            label = "",
        ) { isLoading ->
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    FlowerLoadingIndicator(
                        modifier = Modifier.width(128.dp).height(144.dp).align(Alignment.Center),
                    )
                }
            } else {
                ConstraintLayout(
                    modifier = modifier
                        .semantics {
                            testTagsAsResourceId = true
                        },
                ) {
                    val (toolbar, background, content) = createRefs()
                    TwoTooImageView(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.33f)
                            .constrainAs(background) {
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                        previewPlaceholder = R.drawable.image_home_background,
                        model = R.drawable.image_home_background,
                        contentScale = ContentScale.FillBounds,
                    )

                    when (state.challengeStateUiModel) {
                        is BeforeChallengeUiModel -> {
                            HomeBeforeChallenge(
                                modifier = Modifier.constrainAs(content) {
                                    top.linkTo(toolbar.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                },
                                beforeChallengeUiModel = state.challengeStateUiModel,
                                onClickBeforeChallengeTextButton = onClickBeforeChallengeTextButton,
                            )
                        }

                        is OngoingChallengeUiModel -> {
                            HomeOngoingChallenge(
                                navigateToHistory = navigateToHistory,
                                modifier = Modifier.constrainAs(content) {
                                    top.linkTo(toolbar.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                },
                                ongoingChallengeUiModel = state.challengeStateUiModel,
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
        }
    }
}
