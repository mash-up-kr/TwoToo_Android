package com.mashup.twotoo.presenter.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.onboarding.OnBoardingScreen

const val onBoardingRoute: String = "onboarding_route"

fun NavController.navigateToOnBoarding(navOptions: NavOptions? = null) {
    this.navigate(route = onBoardingRoute, navOptions = navOptions)
}
fun NavGraphBuilder.onBoardingRoute() {
    composable(route = onBoardingRoute) {
        OnBoardingScreen()
    }
}
