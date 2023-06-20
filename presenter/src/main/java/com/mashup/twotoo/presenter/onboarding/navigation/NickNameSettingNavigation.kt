package com.mashup.twotoo.presenter.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.onboarding.NickNameSettingRoute

const val NickNameSettingRoute: String = "nickname_route"

fun NavController.navigateToOnNickNameSetting(navOptions: NavOptions? = null) {
    this.navigate(route = NickNameSettingRoute, navOptions = navOptions)
}
fun NavGraphBuilder.onNickNameSettingGraph(
    onNextButtonClick: () -> Unit
) {
    composable(route = NickNameSettingRoute) {
        NickNameSettingRoute(onNextButtonClick)
    }
}
