package com.mashup.twotoo.presenter.history.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.history.HistoryRoute
import com.mashup.twotoo.presenter.history.datail.HistoryDetailRoute
import com.mashup.twotoo.presenter.history.di.HistoryComponentProvider
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.util.componentProvider

// Todo 일단 이렇게 argument 전송하고 다시 수정해야함
fun NavController.navigateToHistory(challengeNo: Int) {
    this.navigate(route = "${NavigationRoute.HistoryGraph.HistoryScreen.route}/$challengeNo")
}

private fun NavController.navigateToHistoryDetail() {
    this.navigate(route = NavigationRoute.HistoryGraph.HistoryDetailScreen.route)
}

fun NavGraphBuilder.historyGraph(navController: NavController) {
    navigation(startDestination = NavigationRoute.HistoryGraph.HistoryScreen.route, route = NavigationRoute.HistoryGraph.route) {
        composable(
            route = "${NavigationRoute.HistoryGraph.HistoryScreen.route}/{challengeNo}",
            arguments = listOf(
                navArgument("challengeNo") { type = NavType.IntType },
            ),
        ) {
                navBackStackEntry ->
            val challengeNo = navBackStackEntry.arguments?.getInt("challengeNo") ?: 0
            val historyComponent = componentProvider<HistoryComponentProvider>().provideHistoryComponent()
            val historyViewModel = daggerViewModel {
                historyComponent.getViewModel()
            }

            HistoryRoute(
                challengeNo = challengeNo,
                historyViewModel = historyViewModel,
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
