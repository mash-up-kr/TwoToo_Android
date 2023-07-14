package com.mashup.twotoo.presenter.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.home.navigation.homeGraph
import com.mashup.twotoo.presenter.home.navigation.navigateToHome
import com.mashup.twotoo.presenter.invite.navigation.invitationGraph
import com.mashup.twotoo.presenter.invite.navigation.navigateToInvitation
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.nickname.navigation.navigateToOnNickNameSetting
import com.mashup.twotoo.presenter.nickname.navigation.nickNameSettingGraph
import com.mashup.twotoo.presenter.onboarding.OnBoardingRoute
import com.mashup.twotoo.presenter.onboarding.di.OnboardingComponentProvider
import com.mashup.twotoo.presenter.util.componentProvider

fun NavController.navigateToOnBoarding(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.OnBoardingGraph.route, navOptions = navOptions)
}
fun NavGraphBuilder.onBoardingGraph(
    navController: NavController
) {
    composable(route = NavigationRoute.OnBoardingGraph.OnboardingScreen.route) {
        val onBoardingComponent = componentProvider<OnboardingComponentProvider>().provideOnboardingComponent()
        val onBoardingViewModel = daggerViewModel {
            onBoardingComponent.getViewModel()
        }
        OnBoardingRoute(onBoardingViewModel) { route ->
            when (route) {
                NavigationRoute.HomeScreenGraph.HomeScreen.route -> {
                    navController.navigateToHome()
                }
                NavigationRoute.NickNameSettingGraph.NickNameSettingScreen.route -> {
                    navController.navigateToOnNickNameSetting()
                }
                NavigationRoute.InvitationGraph.SendInvitationScreen.route -> {
                    navController.navigateToInvitation()
                }
            }
        }
    }
    nickNameSettingGraph(navController)
    homeGraph(navController)
    invitationGraph(navController)
}
