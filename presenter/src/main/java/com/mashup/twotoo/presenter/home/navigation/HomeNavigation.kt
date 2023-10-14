package com.mashup.twotoo.presenter.home.navigation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.constant.TAG
import com.mashup.twotoo.presenter.createChallenge.navigation.navigateToCreateChallenge
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.garden.navigation.navigateToGarden
import com.mashup.twotoo.presenter.guid.navigation.navigateToGuide
import com.mashup.twotoo.presenter.history.navigation.navigateToHistory
import com.mashup.twotoo.presenter.home.HomeRoute
import com.mashup.twotoo.presenter.home.di.HomeComponentProvider
import com.mashup.twotoo.presenter.home.model.HomeChallengeInfoModel
import com.mashup.twotoo.presenter.mypage.model.GuideUrlItem
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.util.MoshiUtils
import com.mashup.twotoo.presenter.util.componentProvider

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.HomeGraph.route, navOptions = navOptions)
}

fun NavGraphBuilder.homeGraph(
    navController: NavController,
) {
    navigation(startDestination = NavigationRoute.HomeGraph.HomeScreen.route, route = NavigationRoute.HomeGraph.route) {
        composable(
            route = NavigationRoute.HomeGraph.HomeScreen.route,
        ) {
            val homeComponent = componentProvider<HomeComponentProvider>().provideHomeComponent()
            val homeViewModel = daggerViewModel {
                homeComponent.getViewModel()
            }
            HomeRoute(
                homeViewModel = homeViewModel,
                modifier = Modifier.fillMaxSize(),
                navigateToHistory = { challengeNo, from -> navController.navigateToHistory(challengeNo = challengeNo, from = from) },
                navigateToGuide = { navController.navigateToGuide(route = GuideUrlItem.UsingGuide.name) },
                navigateToCreateChallenge = { homeState, challengeInfo ->
                    val challengeInfoJson = MoshiUtils.toJson<HomeChallengeInfoModel>(challengeInfo)
                    Log.d(TAG, "navigateToCreateChallenge: $challengeInfoJson")
                    navController.navigateToCreateChallenge(homeState, challengeInfoJson)
                },
                navigateToGarden = {
                    val topLevelOptions = navOptions {
                        popUpTo(navController.graph.id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    navController.navigateToGarden(it, navOptions = topLevelOptions)
                },
            )
        }
    }
}
