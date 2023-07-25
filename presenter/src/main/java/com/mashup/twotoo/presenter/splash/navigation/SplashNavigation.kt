package com.mashup.twotoo.presenter.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.home.navigation.navigateToHome
import com.mashup.twotoo.presenter.invite.navigation.navigateToInvitation
import com.mashup.twotoo.presenter.invite.navigation.navigateToWaitingAcceptPair
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.nickname.navigation.navigateToOnNickNameSetting
import com.mashup.twotoo.presenter.onboarding.navigation.navigateToOnBoarding
import com.mashup.twotoo.presenter.splash.SplashRoute
import com.mashup.twotoo.presenter.splash.di.SplashComponentProvider
import com.mashup.twotoo.presenter.util.componentProvider

fun NavController.navigateToSplash(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.SplashGraph.route, navOptions = navOptions)
}
fun NavGraphBuilder.splashGraph(
    navController: NavController
) {
    composable(route = NavigationRoute.SplashGraph.SplashScreen.route) {
        val splashComponent = componentProvider<SplashComponentProvider>().provideSplashComponent()
        val splashViewModel = daggerViewModel {
            splashComponent.getViewModel()
        }

        SplashRoute(
            splashViewModel,
        ) { route ->
            when (route) {
                NavigationRoute.HomeGraph.HomeScreen.route -> {
                    navController.navigateToHome()
                }
                NavigationRoute.NickNameSettingGraph.NickNameSettingScreen.route -> {
                    navController.navigateToOnNickNameSetting()
                }
                NavigationRoute.InvitationGraph.SendInvitationScreen.route -> {
                    navController.navigateToInvitation()
                }
                NavigationRoute.InvitationGraph.WaitingAcceptPairScreen.route -> {
                    navController.navigateToWaitingAcceptPair()
                }
                NavigationRoute.OnBoardingGraph.OnboardingScreen.route -> {
                    navController.navigateToOnBoarding()
                }
            }
        }
    }
}
