package com.mashup.twotoo.presenter.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.onboarding.OnBoardingRoute

fun NavController.navigateToOnBoarding(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.OnBoardingGraph.route, navOptions = navOptions)
}
fun NavGraphBuilder.onBoardingGraph(
    onClickLoginButton: () -> Unit
) {
    composable(route = NavigationRoute.OnBoardingGraph.OnboardingScreen.route) {
        OnBoardingRoute(onClickLoginButton)
    }
}
