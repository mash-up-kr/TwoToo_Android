package com.mashup.twotoo.presenter.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.mypage.MyPageRoute

fun NavController.navigateToUser(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.HomeScreenGraph.UserScreen.route, navOptions = navOptions)
}

fun NavGraphBuilder.userGraph() {
    composable(route = NavigationRoute.HomeScreenGraph.UserScreen.route) {
        MyPageRoute()
    }
}
