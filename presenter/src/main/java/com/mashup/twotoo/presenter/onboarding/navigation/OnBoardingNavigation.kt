package com.mashup.twotoo.presenter.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.onboarding.OnBoardingRoute

const val OnBoardingRoute: String = "onboarding_route"

fun NavController.navigateToOnBoarding(navOptions: NavOptions? = null) {
    this.navigate(route = OnBoardingRoute, navOptions = navOptions)
}
fun NavGraphBuilder.onBoardingGraph() {
    composable(route = OnBoardingRoute) {
        OnBoardingRoute()
    }
}
