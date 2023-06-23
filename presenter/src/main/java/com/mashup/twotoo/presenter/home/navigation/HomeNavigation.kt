package com.mashup.twotoo.presenter.home.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.home.HomeComponent
import com.mashup.twotoo.presenter.home.HomeRoute

const val HomeNavigationRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(route = HomeNavigationRoute, navOptions = navOptions)
}
fun NavGraphBuilder.homeGraph() {
    composable(route = HomeNavigationRoute) {
        val homeComponent = (LocalContext.current.applicationContext as HomeComponent.HomeComponentProvider)
            .provideHomeComponent()



        HomeRoute(
            modifier = Modifier.fillMaxSize(),
        )
    }
}
