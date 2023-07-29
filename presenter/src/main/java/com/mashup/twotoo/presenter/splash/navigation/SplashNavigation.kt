package com.mashup.twotoo.presenter.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
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

        val navOptions = navOptions {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
        }

        SplashRoute(
            splashViewModel,
        ) { route ->
            when (route) {
                NavigationRoute.HomeGraph.HomeScreen.route -> {
                    navController.navigateToHome(navOptions = navOptions)
                }
                NavigationRoute.NickNameSettingGraph.NickNameSettingScreen.route -> {
                    navController.navigateToOnNickNameSetting(navOptions = navOptions)
                }
                NavigationRoute.InvitationGraph.SendInvitationScreen.route -> {
                    navController.navigateToInvitation(navOptions = navOptions)
                }
                NavigationRoute.InvitationGraph.WaitingAcceptPairScreen.route -> {
                    navController.navigateToWaitingAcceptPair(navOptions = navOptions)
                }
                NavigationRoute.OnBoardingGraph.OnboardingScreen.route -> {
                    navController.navigateToOnBoarding(navOptions = navOptions)
                }
            }
        }
    }
}
