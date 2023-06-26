package com.mashup.twotoo.presenter.history.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.history.HistoryRoute
import com.mashup.twotoo.presenter.history.datail.HistoryDetailRoute
import com.mashup.twotoo.presenter.navigation.NavigationRoute

fun NavController.navigateToHistory(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.HistoryGraph.route, navOptions = navOptions)
}
private fun NavController.navigateToHistoryDetail() {
    this.navigate(route = NavigationRoute.HistoryGraph.HistoryDetailScreen.route)
}

fun NavGraphBuilder.historyGraph(navController: NavController) {
    navigation(startDestination = NavigationRoute.HistoryGraph.HistoryScreen.route, route = NavigationRoute.HistoryGraph.route) {
        composable(route = NavigationRoute.HistoryGraph.HistoryScreen.route) {
            HistoryRoute(
                onClickBackButton = { navController.popBackStack() },
                navigateToHistoryDetail = {
                    navController.navigateToHistoryDetail()
                },
            )
        }

        composable(route = NavigationRoute.HistoryGraph.HistoryDetailScreen.route) {
            HistoryDetailRoute(onClickBackButton = { navController.popBackStack() })
        }
    }
}
