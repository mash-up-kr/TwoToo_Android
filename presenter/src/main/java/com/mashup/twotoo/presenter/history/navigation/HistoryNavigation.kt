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
    this.navigate(route = NavigationRoute.HistoryScreenGraph.route, navOptions = navOptions)
}
private fun NavController.navigateToHistoryDetail() {
    this.navigate(route = NavigationRoute.HistoryScreenGraph.HistoryDetailScreen.route)
}

fun NavGraphBuilder.historyGraph(navController: NavController) {
    navigation(startDestination = NavigationRoute.HistoryScreenGraph.HistoryScreen.route, route = NavigationRoute.HistoryScreenGraph.route) {
        composable(route = NavigationRoute.HistoryScreenGraph.HistoryScreen.route) {
            HistoryRoute(
                onClickBackButton = { navController.popBackStack() },
                navigateToHistoryDetail = {
                    navController.navigateToHistoryDetail()
                },
            )
        }

        composable(route = NavigationRoute.HistoryScreenGraph.HistoryDetailScreen.route) {
            HistoryDetailRoute(onClickBackButton = { navController.popBackStack() })
        }
    }
}
