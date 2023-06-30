package com.mashup.twotoo.presenter.home.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.history.navigation.historyGraph
import com.mashup.twotoo.presenter.history.navigation.navigateToHistory
import com.mashup.twotoo.presenter.home.HomeRoute
import com.mashup.twotoo.presenter.home.di.HomeComponentProvider
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.util.componentProvider

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.HomeScreenGraph.HomeScreen.route, navOptions = navOptions)
}
fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
) {
    composable(route = NavigationRoute.HomeScreenGraph.HomeScreen.route) {
        val homeComponent = componentProvider<HomeComponentProvider>().provideHomeComponent()
        val homeViewModel = daggerViewModel {
            homeComponent.getViewModel()
        }
        println("homeViewModel instance : ${homeViewModel.hashCode()}")
        val state = homeViewModel.count.collectAsState()

        HomeRoute(
            state = state.value,
            modifier = Modifier.fillMaxSize(),
            onBeeButtonClick = homeViewModel::updateCount,
            navigateToHistory = {
                navController.navigateToHistory()
            },
        )
    }
    historyGraph(navController)
}
