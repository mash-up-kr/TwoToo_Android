package com.mashup.twotoo.presenter.home.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.history.navigation.navigateToHistory
import com.mashup.twotoo.presenter.home.HomeRoute
import com.mashup.twotoo.presenter.home.di.HomeComponentProvider
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.util.componentProvider

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.HomeGraph.route, navOptions = navOptions)
}
fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(startDestination = NavigationRoute.HomeGraph.HomeScreen.route, route = NavigationRoute.HomeGraph.route) {
        composable(route = NavigationRoute.HomeGraph.HomeScreen.route) {
            val homeComponent = componentProvider<HomeComponentProvider>().provideHomeComponent()
            val homeViewModel = daggerViewModel {
                homeComponent.getViewModel()
            }

            HomeRoute(
                modifier = Modifier.fillMaxSize(),
                onBeeButtonClick = homeViewModel::updateCount,
                navigateToHistory = {
                    navController.navigateToHistory()
                },
            )
        }
    }
}
