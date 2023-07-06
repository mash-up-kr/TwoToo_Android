package com.mashup.twotoo.presenter.home.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.createChallenge.navigation.createChallengeGraph
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.history.navigation.historyGraph
import com.mashup.twotoo.presenter.home.HomeRoute
import com.mashup.twotoo.presenter.home.di.HomeComponentProvider
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.util.componentProvider

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.HomeScreenGraph.HomeScreen.route, navOptions = navOptions)
}

fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
) {
    composable(route = NavigationRoute.HomeScreenGraph.HomeScreen.route) {
        val homeComponent = componentProvider<HomeComponentProvider>().provideHomeComponent()
        val homeViewModel = daggerViewModel {
            homeComponent.getViewModel()
        }

        HomeRoute(
            homeViewModel = homeViewModel,
            modifier = Modifier.fillMaxSize(),
            navigateToHistory = homeViewModel::navigateToChallengeDetail,
        )
    }
    historyGraph(navController)
    createChallengeGraph(navController)
}
