package com.mashup.twotoo.presenter.home.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.home.HomeComponentProvider
import com.mashup.twotoo.presenter.home.HomeRoute

const val HomeNavigationRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(route = HomeNavigationRoute, navOptions = navOptions)
}
fun NavGraphBuilder.homeGraph(
    factory: ViewModelProvider.Factory,
) {
    composable(route = HomeNavigationRoute) {
        val homeComponent = (LocalContext.current.applicationContext as HomeComponentProvider).provideHomeComponent()
        val homeViewModel = daggerViewModel(
            key = "homeViewModel",
            factory = factory,
            viewModelInstanceCreator = { homeComponent.getHomeViewModel() },
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
