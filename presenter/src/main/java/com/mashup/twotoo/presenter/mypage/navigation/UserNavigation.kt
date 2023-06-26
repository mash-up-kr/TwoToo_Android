package com.mashup.twotoo.presenter.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.mypage.MyPageRoute
import com.mashup.twotoo.presenter.navigation.NavigationRoute

fun NavController.navigateToUser(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.UserGraph.route, navOptions = navOptions)
}

fun NavGraphBuilder.userGraph(navController: NavController) {
    navigation(startDestination = NavigationRoute.UserGraph.UserScreen.route, route = NavigationRoute.UserGraph.route) {
        composable(route = NavigationRoute.UserGraph.UserScreen.route) {
            MyPageRoute()
        }
    }
}
