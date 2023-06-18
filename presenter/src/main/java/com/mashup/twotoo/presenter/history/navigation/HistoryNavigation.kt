package com.mashup.twotoo.presenter.history.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.history.HistoryRoute

const val HistoryNavigationRoute = "history_route"

fun NavController.navigateToHistory(navOptions: NavOptions? = null) {
    this.navigate(route = HistoryNavigationRoute, navOptions = navOptions)
}

fun NavGraphBuilder.historyGraph(navController: NavController) {
    composable(route = HistoryNavigationRoute) {
        HistoryRoute(onClickBackButton = { navController.popBackStack() })
    }
}
