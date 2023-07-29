package com.mashup.twotoo.presenter.guid.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.guid.GuideRoute
import com.mashup.twotoo.presenter.navigation.NavigationRoute

fun NavController.navigateToGuide(route: String) {
    this.navigate(route = "${NavigationRoute.GuideGraph.GuideWebView.route}/$route")
}

fun NavGraphBuilder.guidGraph(navController: NavController) {
    navigation(
        startDestination = "${NavigationRoute.GuideGraph.GuideWebView.route}/{route}",
        route = NavigationRoute.GuideGraph.route,
    ) {
        composable(
            route = "${NavigationRoute.GuideGraph.GuideWebView.route}/{route}",
            arguments = listOf(
                navArgument("route") { type = NavType.StringType },
            ),
        ) { navBackStackEntry ->
            val route = navBackStackEntry.arguments?.getString("route") ?: ""
            GuideRoute(
                route = route,
                onClickBackButton = {
                    navController.popBackStack()
                },
            )
        }
    }
}
