package com.mashup.twotoo.presenter.home.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.history.navigation.navigateToHistory
import com.mashup.twotoo.presenter.home.HomeRoute

const val HomeNavigationRoute = "home_route"

fun NavController.navigateToHome() {
    this.navigate(route = HomeNavigationRoute)
}
fun NavGraphBuilder.homeGraph(navController: NavController) {
    composable(route = HomeNavigationRoute) {
        HomeRoute(
            modifier = Modifier.fillMaxSize(),
            navigateToHistory = {
                navController.navigateToHistory()
            },
        )
    }
}
