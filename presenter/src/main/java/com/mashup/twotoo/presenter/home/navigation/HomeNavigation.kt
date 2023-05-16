package com.mashup.twotoo.presenter.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.home.HomeRoute

const val homeNavigationRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(route = homeNavigationRoute, navOptions = navOptions)
}
fun NavGraphBuilder.homeGraph() {
    composable(route = homeNavigationRoute) {
        HomeRoute()
    }
}
