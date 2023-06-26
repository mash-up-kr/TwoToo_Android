package com.mashup.twotoo.presenter.garden.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.garden.GardenRoute
import com.mashup.twotoo.presenter.history.navigation.navigateToHistory
import com.mashup.twotoo.presenter.navigation.NavigationRoute

fun NavController.navigateToGarden(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.GardenGraph.route, navOptions = navOptions)
}

fun NavGraphBuilder.gardenGraph(navController: NavController) {
    navigation(startDestination = NavigationRoute.GardenGraph.GardenScreen.route, route = NavigationRoute.GardenGraph.route) {
        composable(route = NavigationRoute.GardenGraph.GardenScreen.route) {
            GardenRoute(navigateToGarden = { navController.navigateToHistory() })
        }
    }
}
