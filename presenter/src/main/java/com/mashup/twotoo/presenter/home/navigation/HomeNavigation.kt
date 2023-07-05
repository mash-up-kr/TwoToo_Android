package com.mashup.twotoo.presenter.home.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.createChallenge.navigation.createChallengeGraph
import com.mashup.twotoo.presenter.createChallenge.navigation.navigateToCreateChallenge
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetType
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.TwoTooBottomSheet
import com.mashup.twotoo.presenter.designsystem.component.toast.SnackBarHost
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.history.navigation.historyGraph
import com.mashup.twotoo.presenter.history.navigation.navigateToHistory
import com.mashup.twotoo.presenter.home.HomeRoute
import com.mashup.twotoo.presenter.home.di.HomeComponentProvider
import com.mashup.twotoo.presenter.home.model.HomeSideEffect.NavigateToChallengeDetail
import com.mashup.twotoo.presenter.home.model.HomeSideEffect.NavigationToCreateChallenge
import com.mashup.twotoo.presenter.home.model.HomeSideEffect.OpenToAuthBottomSheet
import com.mashup.twotoo.presenter.home.model.HomeSideEffect.OpenToCheerBottomSheet
import com.mashup.twotoo.presenter.home.model.HomeSideEffect.OpenToHelp
import com.mashup.twotoo.presenter.home.model.HomeSideEffect.OpenToShotBottomSheet
import com.mashup.twotoo.presenter.home.model.HomeSideEffect.Toast
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.util.componentProvider
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.HomeScreenGraph.HomeScreen.route, navOptions = navOptions)
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
) {
    composable(route = NavigationRoute.HomeScreenGraph.HomeScreen.route) {
        val homeComponent = componentProvider<HomeComponentProvider>().provideHomeComponent()
        val homeViewModel = daggerViewModel {
            homeComponent.getViewModel()
        }

        var isBottomSheetVisible by rememberSaveable {
            mutableStateOf(false)
        }
        var bottomSheetType by remember {
            mutableStateOf<BottomSheetType>(BottomSheetType.Authenticate())
        }

        val bottomSheetState = rememberModalBottomSheetState(true)
        val coroutineScope = rememberCoroutineScope()

        val snackState = remember { SnackbarHostState() }

        val state by homeViewModel.collectAsState()

        homeViewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is Toast -> {
                    snackState.showSnackbar(
                        sideEffect.text,
                    )
                }
                is OpenToShotBottomSheet -> {
                    bottomSheetType = BottomSheetType.SendType.Shot()
                    isBottomSheetVisible = !isBottomSheetVisible
                }
                is OpenToCheerBottomSheet -> {
                    bottomSheetType = BottomSheetType.SendType.Cheer()
                    isBottomSheetVisible = !isBottomSheetVisible
                }
                is OpenToAuthBottomSheet -> {
                    bottomSheetType = BottomSheetType.Authenticate()
                    isBottomSheetVisible = !isBottomSheetVisible
                }
                is OpenToHelp -> {
                }
                is NavigateToChallengeDetail -> {
                    navController.navigateToHistory()
                }
                is NavigationToCreateChallenge -> {
                    navController.navigateToCreateChallenge()
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            HomeRoute(
                state = state,
                modifier = Modifier.fillMaxSize(),
                onBeeButtonClick = homeViewModel::openToShotBottomSheet,
                navigateToHistory = homeViewModel::navigateToChallengeDetail,
                onClickBeforeChallengeTextButton = homeViewModel::onClickBeforeChallengeTextButton,
            )

            if (isBottomSheetVisible) {
                TwoTooBottomSheet(
                    bottomSheetState = bottomSheetState,
                    type = bottomSheetType,
                    onDismiss = {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                        }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                isBottomSheetVisible = !isBottomSheetVisible
                            }
                        }
                    },
                    onClickButton = homeViewModel::onClickSendBottomSheetDataButton,
                )
            }

            SnackBarHost(
                Modifier.align(Alignment.BottomCenter),
                snackState,
            )
        }
    }
    historyGraph(navController)
    createChallengeGraph(navController)
}
