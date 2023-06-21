package com.mashup.twotoo.presenter.garden.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.garden.GardenRoute
import com.mashup.twotoo.presenter.navigation.NavigationRoute

fun NavController.navigateToGarden(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.HomeScreenGraph.GardenScreen.route, navOptions = navOptions)
}

fun NavGraphBuilder.gardenGraph() {
    composable(route = NavigationRoute.HomeScreenGraph.GardenScreen.route) {
        GardenRoute()
    }
}
