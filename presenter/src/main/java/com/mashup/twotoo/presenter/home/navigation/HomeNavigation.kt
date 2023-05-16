package com.mashup.twotoo.presenter.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.home.HomeScreen

/**
 * @Created by 김현국 2023/05/16
 * @Time 3:54 PM
 */

const val homeNavigationRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(route = homeNavigationRoute, navOptions = navOptions)
}
fun NavGraphBuilder.homeRoute() {
    composable(route = homeNavigationRoute) {
        HomeScreen()
    }
}
