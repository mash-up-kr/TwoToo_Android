package com.mashup.twotoo.presenter.guid.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.guid.GuideRoute
import com.mashup.twotoo.presenter.navigation.NavigationRoute

fun NavController.navigateToGuide(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.GuideGraph.route, navOptions = navOptions)
}

fun NavGraphBuilder.guidGraph(navController: NavController) {
    navigation(startDestination = NavigationRoute.GuideGraph.GuideWebView.route, route = NavigationRoute.GuideGraph.route) {
        composable(route = NavigationRoute.GuideGraph.GuideWebView.route) {
            GuideRoute(
                onClickBackButton = {
                    navController.popBackStack()
                },
            )
        }
    }
}
