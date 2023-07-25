package com.mashup.twotoo.presenter.createChallenge.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.createChallenge.CreateChallengeRoute
import com.mashup.twotoo.presenter.createChallenge.SuccessChallengeRequest
import com.mashup.twotoo.presenter.createChallenge.di.CreateChallengeProvider
import com.mashup.twotoo.presenter.createChallenge.selectflower.SelectFlowerCardRoute
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.home.navigation.navigateToHome
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.util.componentProvider

fun NavController.navigateToCreateChallenge(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.CreateChallengeGraph.route, navOptions = navOptions)
}

private fun NavController.navigateToSelectFlowerCard(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.CreateChallengeGraph.SelectFlowerCardScreen.route, navOptions = navOptions)
}

private fun NavController.navigateToSuccessChallengeRequest(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.CreateChallengeGraph.SuccessChallengeRequest.route, navOptions = navOptions)
}

@SuppressLint("UnrememberedGetBackStackEntry")
fun NavGraphBuilder.createChallengeGraph(
    navController: NavController
) {
    navigation(
        startDestination = NavigationRoute.CreateChallengeGraph.CreateChallengeScreen.route,
        route = NavigationRoute.CreateChallengeGraph.route,
    ) {
        composable(route = NavigationRoute.CreateChallengeGraph.CreateChallengeScreen.route) {
            val challengeComponent = componentProvider<CreateChallengeProvider>().provideCreateChallengeComponent()
            val createChallengeViewModel = daggerViewModel {
                challengeComponent.getViewModel()
            }
            CreateChallengeRoute(
                createChallengeViewModel = createChallengeViewModel,
            ) {
                navController.navigateToSelectFlowerCard()
            }
        }
        composable(route = NavigationRoute.CreateChallengeGraph.SelectFlowerCardScreen.route) {
                navBackStackEntry ->
            val parentRoute = NavigationRoute.CreateChallengeGraph.CreateChallengeScreen.route
            val parentEntry = remember {
                navController.getBackStackEntry(parentRoute)
            }
            val viewModelStoreOwner: ViewModelStoreOwner = parentEntry

            val challengeComponent = componentProvider<CreateChallengeProvider>().provideCreateChallengeComponent()
            val createChallengeViewModel = daggerViewModel(viewModelStoreOwner = viewModelStoreOwner) {
                challengeComponent.getViewModel()
            }
            SelectFlowerCardRoute(
                createChallengeViewModel,
                onClickBackButton = {
                    navController.popBackStack()
                },
                onSuccessCreateChallenge = {
                    navController.navigateToSuccessChallengeRequest()
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
