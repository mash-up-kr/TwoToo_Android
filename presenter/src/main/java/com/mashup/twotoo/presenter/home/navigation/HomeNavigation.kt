package com.mashup.twotoo.presenter.home.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.home.HomeRoute
import com.mashup.twotoo.presenter.home.di.HomeComponentProvider

const val HomeNavigationRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(route = HomeNavigationRoute, navOptions = navOptions)
}
fun NavGraphBuilder.homeGraph() {
    composable(route = HomeNavigationRoute) {
        val homeComponent = (LocalContext.current.applicationContext as HomeComponentProvider).provideHomeComponent()
        val homeViewModel = daggerViewModel(viewModelStoreOwner = it) {
            homeComponent.getViewModel()
        }
        println("homeViewModel instance : ${homeViewModel.hashCode()}")
        val state = homeViewModel.count.collectAsState()
        HomeRoute(
            state = state.value,
            modifier = Modifier.fillMaxSize(),
            onBeeButtonClick = homeViewModel::updateCount,
        )
    }
}
