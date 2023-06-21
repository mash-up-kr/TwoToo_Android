package com.mashup.twotoo.presenter.history.datail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.history.datail.HistoryDetailRoute
import com.mashup.twotoo.presenter.navigation.NavigationRoute

fun NavController.navigateToHistoryDetail() {
    this.navigate(route = NavigationRoute.HistoryScreenGraph.HistoryDetailScreen.route)
}

fun NavGraphBuilder.historyDetailGraph(navController: NavController) {
    composable(route = NavigationRoute.HistoryScreenGraph.HistoryDetailScreen.route) {
        HistoryDetailRoute(onClickBackButton = { navController.popBackStack() })
    }
}
