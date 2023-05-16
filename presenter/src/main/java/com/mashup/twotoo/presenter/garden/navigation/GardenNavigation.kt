package com.mashup.twotoo.presenter.garden.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.garden.GardenScreen

/**
 * @Created by 김현국 2023/05/16
 * @Time 3:52 PM
 */

const val gardenNavigationRoute = "garden_route"

fun NavController.navigateToGarden(navOptions: NavOptions? = null) {
    this.navigate(route = gardenNavigationRoute, navOptions = navOptions)
}

fun NavGraphBuilder.gardenRoute() {
    composable(route = gardenNavigationRoute) {
        GardenScreen()
    }
}
