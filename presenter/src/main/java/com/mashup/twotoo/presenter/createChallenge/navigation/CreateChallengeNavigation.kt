package com.mashup.twotoo.presenter.createChallenge.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.createChallenge.CreateChallenge
import com.mashup.twotoo.presenter.createChallenge.SelectFlowerCardRoute
import com.mashup.twotoo.presenter.createChallenge.SuccessChallengeRequest
import com.mashup.twotoo.presenter.navigation.NavigationRoute

fun NavController.navigateToCreateChallenge(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.CreateChallengeGraph.route, navOptions = navOptions)
}

private fun NavController.navigateToSelectFlowerCard(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.CreateChallengeGraph.SelectFlowerCardScreen.route, navOptions = navOptions)
}

private fun NavController.navigateToSuccessChallengeRequest(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.CreateChallengeGraph.route, navOptions = navOptions)
}

fun NavGraphBuilder.createChallengeGraph(
    navController: NavController
) {
    navigation(
        startDestination = NavigationRoute.CreateChallengeGraph.CreateChallengeScreen.route,
        route = NavigationRoute.CreateChallengeGraph.route,
    ) {
        composable(route = NavigationRoute.CreateChallengeGraph.CreateChallengeScreen.route) {
            CreateChallenge {
                navController.navigateToSelectFlowerCard()
            }
        }
        composable(route = NavigationRoute.CreateChallengeGraph.SelectFlowerCardScreen.route) {
            SelectFlowerCardRoute {
                navController.navigateToSuccessChallengeRequest()
            }
        }
        composable(route = NavigationRoute.CreateChallengeGraph.SuccessChallengeRequest.route) {
            SuccessChallengeRequest()
        }
    }
}
