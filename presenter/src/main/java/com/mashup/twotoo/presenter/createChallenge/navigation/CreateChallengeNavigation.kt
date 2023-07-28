package com.mashup.twotoo.presenter.createChallenge.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.constant.TAG
import com.mashup.twotoo.presenter.createChallenge.CreateChallengeRoute
import com.mashup.twotoo.presenter.createChallenge.SuccessChallengeRequest
import com.mashup.twotoo.presenter.createChallenge.di.CreateChallengeProvider
import com.mashup.twotoo.presenter.createChallenge.selectflower.SelectFlowerCardRoute
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.home.model.BeforeChallengeState
import com.mashup.twotoo.presenter.home.model.HomeChallengeInfoModel
import com.mashup.twotoo.presenter.home.navigation.navigateToHome
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.util.MoshiUtils
import com.mashup.twotoo.presenter.util.componentProvider

fun NavController.navigateToCreateChallenge(homeState: BeforeChallengeState, challengeInfo: String, navOptions: NavOptions? = null) {
    Log.d(TAG, "navigateToCreateChallengeNavigation: $challengeInfo")
    this.navigate(route = "${NavigationRoute.CreateChallengeGraph.CreateChallengeScreen.route}/${homeState.name}/$challengeInfo", navOptions = navOptions)
}

private fun NavController.navigateToSelectFlowerCard(challengeNo: Int, homeState: String, navOptions: NavOptions? = null) {
    this.navigate(route = "${NavigationRoute.CreateChallengeGraph.SelectFlowerCardScreen.route}/$homeState/$challengeNo", navOptions = navOptions)
}

private fun NavController.navigateToSuccessChallengeRequest(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.CreateChallengeGraph.SuccessChallengeRequest.route, navOptions = navOptions)
}

@SuppressLint("UnrememberedGetBackStackEntry")
fun NavGraphBuilder.createChallengeGraph(
    navController: NavController
) {
    navigation(
        startDestination = "${NavigationRoute.CreateChallengeGraph.CreateChallengeScreen.route}/{homeState}/{challengeInfo}",
        route = NavigationRoute.CreateChallengeGraph.route,
    ) {
        composable(
            route = "${NavigationRoute.CreateChallengeGraph.CreateChallengeScreen.route}/{homeState}/{challengeInfo}",
            arguments = listOf(
                navArgument("homeState") { type = NavType.StringType },
                navArgument("challengeInfo") { type = NavType.StringType },
            ),
        ) { navBackStackEntry ->
            val homeState = navBackStackEntry.arguments?.getString("homeState") ?: BeforeChallengeState.EMPTY.name
            val challengeInfoJson = navBackStackEntry.arguments?.getString("challengeInfo") ?: ""
            val challengeInfo = MoshiUtils.fromJson<HomeChallengeInfoModel>(challengeInfoJson) ?: HomeChallengeInfoModel()
            var step = 1
            val challengeComponent = componentProvider<CreateChallengeProvider>().provideCreateChallengeComponent()
            val createChallengeViewModel = daggerViewModel {
                challengeComponent.getViewModel()
            }
            Log.d(TAG, "createChallengeGraph: $challengeInfo")
            when (homeState) {
                BeforeChallengeState.EMPTY.name, BeforeChallengeState.TERMINATION.name -> {
                    step = 1
                }
                else -> {
                    step = 3
                    createChallengeViewModel.setHomeChallengeInfo(challengeInfo)
                }
            }

            CreateChallengeRoute(
                homeState = homeState,
                step = step,
                createChallengeViewModel = createChallengeViewModel,
                onBackToHome = { navController.popBackStack() },
            ) {
                navController.navigateToSelectFlowerCard(
                    challengeNo = challengeInfo.challengeNo,
                    homeState = homeState,
                )
            }
        }
        composable(
            route = "${NavigationRoute.CreateChallengeGraph.SelectFlowerCardScreen.route}/{homeState}/{challengeNo}",
            arguments = listOf(
                navArgument("homeState") { type = NavType.StringType },
                navArgument("challengeNo") { type = NavType.IntType },
            ),
        ) { navBackStackEntry ->
            val homeState = navBackStackEntry.arguments?.getString("homeState") ?: BeforeChallengeState.EMPTY.name
            val challengeNo = navBackStackEntry.arguments?.getInt("challengeNo") ?: 0
            val parentRoute = "${NavigationRoute.CreateChallengeGraph.CreateChallengeScreen.route}/{homeState}/{challengeInfo}"
            val parentEntry = remember {
                navController.getBackStackEntry(parentRoute)
            }
            val viewModelStoreOwner: ViewModelStoreOwner = parentEntry

            val challengeComponent = componentProvider<CreateChallengeProvider>().provideCreateChallengeComponent()
            val createChallengeViewModel = daggerViewModel(viewModelStoreOwner = viewModelStoreOwner) {
                challengeComponent.getViewModel()
            }
            SelectFlowerCardRoute(
                challengeNo = challengeNo,
                homeState = homeState,
                createChallengeViewModel = createChallengeViewModel,
                onClickBackButton = {
                    navController.popBackStack()
                },
                onSuccessCreateChallenge = {
                    if (homeState == BeforeChallengeState.EMPTY.name || homeState == BeforeChallengeState.TERMINATION.name) {
                        navController.navigateToSuccessChallengeRequest(
                            navOptions = navOptions {
                                popUpTo(NavigationRoute.CreateChallengeGraph.route) {
                                    inclusive = true
                                }
                            },
                        )
                    } else {
                        navController.navigateToHome()
                    }
                },
            )
        }
        composable(route = NavigationRoute.CreateChallengeGraph.SuccessChallengeRequest.route) {
            SuccessChallengeRequest {
                navController.navigateToHome()
            }
        }
    }
}
