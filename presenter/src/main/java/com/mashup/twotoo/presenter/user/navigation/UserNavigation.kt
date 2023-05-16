package com.mashup.twotoo.presenter.user.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.user.UserScreen

/**
 * @Created by 김현국 2023/05/16
 * @Time 3:54 PM
 */
const val userNavigationRoute = "user_route"

fun NavController.navigateToUser(navOptions: NavOptions? = null) {
    this.navigate(route = userNavigationRoute, navOptions = navOptions)
}

fun NavGraphBuilder.userRoute() {
    composable(route = userNavigationRoute) {
        UserScreen()
    }
}
