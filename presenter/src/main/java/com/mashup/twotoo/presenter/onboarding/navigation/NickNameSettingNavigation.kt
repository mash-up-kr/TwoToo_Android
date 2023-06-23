package com.mashup.twotoo.presenter.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.onboarding.NickNameSettingRoute

fun NavController.navigateToOnNickNameSetting(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.NickNameSettingGraph.route, navOptions = navOptions)
}
fun NavGraphBuilder.onNickNameSettingGraph(
    onNextButtonClick: () -> Unit
) {
    composable(route = NavigationRoute.NickNameSettingGraph.NickNameSettingScreen.route) {
        NickNameSettingRoute(onNextButtonClick)
    }
}
