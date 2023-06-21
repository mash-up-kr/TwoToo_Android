package com.mashup.twotoo.presenter.createChallenge.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.createChallenge.CreateChallenge
import com.mashup.twotoo.presenter.createChallenge.SelectFlowerCard
import com.mashup.twotoo.presenter.createChallenge.SelectFlowerCardRoute
import com.mashup.twotoo.presenter.createChallenge.SuccessChallengeRequest

sealed class CreateChallengeNavigation(val route: String) {
    object CreateChallenge : CreateChallengeNavigation("create_challenge")
    object SelectFlowerCard : CreateChallengeNavigation("select_flower_card")
    object SuccessChallengeRequest : CreateChallengeNavigation("success_challenge_request")
}

fun NavController.navigateToCreateChallenge(navOptions: NavOptions? = null) {
    this.navigate(route = CreateChallengeNavigation.CreateChallenge.route, navOptions = navOptions)
}
fun NavGraphBuilder.createChallengeGraph(
    navController: NavController
) {
    navigation(
        startDestination = CreateChallengeNavigation.CreateChallenge.route,
        route = "CreateChallengeNavigation",
    ) {
        composable(route = CreateChallengeNavigation.CreateChallenge.route) {
            CreateChallenge {
                navController.navigate(CreateChallengeNavigation.SelectFlowerCard.route)
            }
        }
        composable(route = CreateChallengeNavigation.SelectFlowerCard.route) {
            SelectFlowerCardRoute {
                navController.navigate(CreateChallengeNavigation.SuccessChallengeRequest.route)
            }
        }
        composable(route = CreateChallengeNavigation.SuccessChallengeRequest.route) {
            SuccessChallengeRequest()
        }
    }
}
