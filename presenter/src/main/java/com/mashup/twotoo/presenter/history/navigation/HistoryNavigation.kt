package com.mashup.twotoo.presenter.history.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.history.HistoryRoute
import com.mashup.twotoo.presenter.history.datail.navigation.navigateToHistoryDetail
import com.mashup.twotoo.presenter.navigation.NavigationRoute

fun NavController.navigateToHistory(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.HistoryScreenGraph.HistoryScreen.route, navOptions = navOptions)
}

fun NavGraphBuilder.historyGraph(navController: NavController) {
    composable(route = NavigationRoute.HistoryScreenGraph.HistoryScreen.route) {
        HistoryRoute(
            onClickBackButton = { navController.popBackStack() },
            navigateToHistoryDetail = {
                navController.navigateToHistoryDetail()
            },
        )
    }
}
