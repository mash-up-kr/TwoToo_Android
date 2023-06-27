package com.mashup.twotoo.presenter.home.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.home.HomeRoute
import com.mashup.twotoo.presenter.home.HomeViewModel

const val HomeNavigationRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(route = HomeNavigationRoute, navOptions = navOptions)
}
fun NavGraphBuilder.homeGraph(
    factory: ViewModelProvider.Factory,
) {
    composable(route = HomeNavigationRoute) {
        val homeViewModel = daggerViewModel<HomeViewModel>(
            factory = factory,
        )
        val state = homeViewModel.count.collectAsState()
        homeViewModel.printHashCode()
        HomeRoute(
            modifier = Modifier.fillMaxSize(),
            onBeeButtonClick = homeViewModel::updateCount,
            count = state.value,
        )
    }
}
