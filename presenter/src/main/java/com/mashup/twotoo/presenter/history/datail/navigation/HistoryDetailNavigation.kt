package com.mashup.twotoo.presenter.history.datail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.history.datail.HistoryDetailRoute

const val HistoryDetailNavigationRoute = "history_detail_route"

fun NavController.navigateToHistoryDetail() {
    this.navigate(route = HistoryDetailNavigationRoute)
}

fun NavGraphBuilder.historyDetailGraph(navController: NavController) {
    composable(route = HistoryDetailNavigationRoute) {
        HistoryDetailRoute(onClickBackButton = { navController.popBackStack() })
    }
}
