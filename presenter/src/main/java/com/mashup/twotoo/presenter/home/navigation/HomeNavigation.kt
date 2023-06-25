package com.mashup.twotoo.presenter.home.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.history.navigation.historyGraph
import com.mashup.twotoo.presenter.history.navigation.navigateToHistory
import com.mashup.twotoo.presenter.home.HomeRoute
import com.mashup.twotoo.presenter.navigation.NavigationRoute

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.HomeScreenGraph.HomeScreen.route, navOptions = navOptions)
}
fun NavGraphBuilder.homeGraph(navController: NavController) {
    composable(route = NavigationRoute.HomeScreenGraph.HomeScreen.route) {
        HomeRoute(
            modifier = Modifier.fillMaxSize(),
            navigateToHistory = {
                navController.navigateToHistory()
            },
        )
    }
    historyGraph(navController)
}
