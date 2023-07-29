package com.mashup.twotoo.presenter.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.guid.navigation.navigateToGuide
import com.mashup.twotoo.presenter.mypage.MyPageRoute
import com.mashup.twotoo.presenter.mypage.di.UserComponentProvider
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.util.componentProvider

fun NavController.navigateToUser(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.UserGraph.route, navOptions = navOptions)
}

fun NavGraphBuilder.userGraph(navController: NavController) {
    navigation(startDestination = NavigationRoute.UserGraph.UserScreen.route, route = NavigationRoute.UserGraph.route) {
        composable(route = NavigationRoute.UserGraph.UserScreen.route) {
            val userComponent = componentProvider<UserComponentProvider>().provideUserComponent()
            val userViewModel = daggerViewModel {
                userComponent.getViewModel()
            }

            MyPageRoute(
                userViewModel = userViewModel,
                navigateToGuide = { url -> navController.navigateToGuide(url) },
            )
        }
    }
}
