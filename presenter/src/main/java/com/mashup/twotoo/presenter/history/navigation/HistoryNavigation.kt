package com.mashup.twotoo.presenter.history.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.history.HistoryRoute
import com.mashup.twotoo.presenter.history.datail.HistoryDetailRoute
import com.mashup.twotoo.presenter.history.detailImage.DetailImageRoute
import com.mashup.twotoo.presenter.history.di.HistoryComponentProvider
import com.mashup.twotoo.presenter.home.di.HomeComponentProvider
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.util.componentProvider
import com.mashup.twotoo.presenter.util.toIncodeUrl

fun NavController.navigateToHistory(challengeNo: Int, from: String = "twotoo") {
    this.navigate(route = "${NavigationRoute.HistoryGraph.HistoryScreen.route}/$challengeNo/$from")
}

fun NavController.navigateToHistoryDetail(commitNo: Int) {
    this.navigate(route = "${NavigationRoute.HistoryGraph.HistoryDetailScreen.route}/$commitNo")
}

fun NavController.navigateToHistoryDetailWithHomeViewModel() {
    this.navigate(route = NavigationRoute.HistoryGraph.HistoryDetailScreenWithHomeViewModel.route)
}

private fun NavController.navigateToDetailImageScreen(url: String) {
    this.navigate(route = "${NavigationRoute.HistoryGraph.DetailImageScreen.route}/${url.toIncodeUrl()}")
}

@SuppressLint("UnrememberedGetBackStackEntry")
fun NavGraphBuilder.historyGraph(navController: NavController) {
    navigation(startDestination = "${NavigationRoute.HistoryGraph.HistoryScreen.route}/{challengeNo}/{from}", route = NavigationRoute.HistoryGraph.route) {
        composable(
            route = "${NavigationRoute.HistoryGraph.HistoryScreen.route}/{challengeNo}/{from}",
            arguments = listOf(
                navArgument("challengeNo") { type = NavType.IntType },
                navArgument("from") {
                    defaultValue = "twotoo"
                    type = NavType.StringType
                },

            ),
        ) {
                navBackStackEntry ->
            val challengeNo = navBackStackEntry.arguments?.getInt("challengeNo") ?: 0
            val from = navBackStackEntry.arguments?.getString("from") ?: "twotoo"

            val historyComponent = componentProvider<HistoryComponentProvider>().provideHistoryComponent()
            val historyViewModel = daggerViewModel {
                historyComponent.getViewModel()
            }
            HistoryRoute(
                from = from,
                challengeNo = challengeNo,
                historyViewModel = historyViewModel,
                onClickBackButton = { navController.popBackStack() },
                navigateToHistoryDetail = { commitNo ->
                    navController.navigateToHistoryDetail(commitNo)
                },
            )
        }

        composable(
            route = "${NavigationRoute.HistoryGraph.HistoryDetailScreen.route}/{commitNo}",
            arguments = listOf(
                navArgument("commitNo") { type = NavType.IntType },
            ),
        ) {
                navBackStackEntry ->

            val parentRoute = "${NavigationRoute.HistoryGraph.HistoryScreen.route}/{challengeNo}/{from}"
            val parentEntry = remember {
                navController.getBackStackEntry(parentRoute)
            }
            val viewModelStoreOwner: ViewModelStoreOwner = parentEntry

            val commitNo = navBackStackEntry.arguments?.getInt("commitNo") ?: 0
            val historyComponent = componentProvider<HistoryComponentProvider>().provideHistoryComponent()
            val historyViewModel = daggerViewModel(viewModelStoreOwner = viewModelStoreOwner) {
                historyComponent.getViewModel()
            }
            HistoryDetailRoute(
                commitNo = commitNo,
                historyViewModel = historyViewModel,
                onClickBackButton = navController::popBackStack,
                onClickImage = navController::navigateToDetailImageScreen,
            )
        }

        composable(
            route = NavigationRoute.HistoryGraph.HistoryDetailScreenWithHomeViewModel.route,
        ) {
            val parentRoute = NavigationRoute.HomeGraph.HomeScreen.route
            val parentEntry = remember {
                navController.getBackStackEntry(parentRoute)
            }
            val viewModelStoreOwner: ViewModelStoreOwner = parentEntry

            val historyComponent = componentProvider<HomeComponentProvider>().provideHomeComponent()
            val homeViewModel = daggerViewModel(viewModelStoreOwner = viewModelStoreOwner) {
                historyComponent.getViewModel()
            }
            HistoryDetailRoute(
                homeViewModel = homeViewModel,
                onClickBackButton = navController::popBackStack,
                onClickImage = navController::navigateToDetailImageScreen,
            )
        }

        composable(
            route = "${NavigationRoute.HistoryGraph.DetailImageScreen.route}/{url}",
            arguments = listOf(
                navArgument("url") { type = NavType.StringType },
            ),
        ) { navBackStackEntry: NavBackStackEntry ->

            val url = navBackStackEntry.arguments?.getString("url") ?: ""
            SideEffect {
                Log.d("Exception", "historyGraph: $url")
            }
            DetailImageRoute(
                url = url,
                onClickBackButton = navController::popBackStack,
            )
        }
    }
}
