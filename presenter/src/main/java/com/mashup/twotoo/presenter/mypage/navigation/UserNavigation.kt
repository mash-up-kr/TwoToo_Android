package com.mashup.twotoo.presenter.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.mypage.MyPageRoute

const val UserNavigationRoute = "user_route"

fun NavController.navigateToUser(navOptions: NavOptions? = null) {
    this.navigate(route = UserNavigationRoute, navOptions = navOptions)
}

fun NavGraphBuilder.userGraph() {
    composable(route = UserNavigationRoute) {
        MyPageRoute()
    }
}
