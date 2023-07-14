package com.mashup.twotoo.presenter.nickname.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.home.navigation.navigateToHome
import com.mashup.twotoo.presenter.invite.navigation.navigateToInvitation
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.nickname.NickNameSettingRoute
import com.mashup.twotoo.presenter.nickname.di.NickNameSettingComponentProvider
import com.mashup.twotoo.presenter.util.componentProvider

fun NavController.navigateToOnNickNameSetting(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.NickNameSettingGraph.route, navOptions = navOptions)
}
fun NavGraphBuilder.nickNameSettingGraph(
    navController: NavController
) {
    navigation(startDestination = NavigationRoute.NickNameSettingGraph.NickNameSettingScreen.route, route = NavigationRoute.NickNameSettingGraph.route) {
        composable(route = NavigationRoute.NickNameSettingGraph.NickNameSettingScreen.route) {
            val nickNameSettingComponent = componentProvider<NickNameSettingComponentProvider>().provideNickNameSettingComponent()
            val nickNameViewModel = daggerViewModel {
                nickNameSettingComponent.getViewModel()
            }
            NickNameSettingRoute(nickNameViewModel) { route ->
                when (route) {
                    NavigationRoute.HomeGraph.HomeScreen.route -> {
                        navController.navigateToHome()
                    }
                    NavigationRoute.InvitationGraph.SendInvitationScreen.route -> {
                        navController.navigateToInvitation()
                    }
                }
            }
        }
    }
}
