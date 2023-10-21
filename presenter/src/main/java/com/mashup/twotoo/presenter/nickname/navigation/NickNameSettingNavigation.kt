package com.mashup.twotoo.presenter.nickname.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.home.navigation.navigateToHome
import com.mashup.twotoo.presenter.invite.navigation.navigateToInvitation
import com.mashup.twotoo.presenter.mypage.navigation.navigateToUser
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.nickname.NickNameSettingRoute
import com.mashup.twotoo.presenter.nickname.di.NickNameSettingComponentProvider
import com.mashup.twotoo.presenter.util.componentProvider

fun NavController.navigateToOnNickNameSetting(startRoute: String = "splash", navOptions: NavOptions? = null) {
    this.navigate(route = "${NavigationRoute.NickNameSettingGraph.NickNameSettingScreen.route}/$startRoute", navOptions = navOptions)
}
fun NavGraphBuilder.nickNameSettingGraph(
    navController: NavController
) {
    navigation(
        startDestination = "${NavigationRoute.NickNameSettingGraph.NickNameSettingScreen.route}/{startRoute}",
        route = NavigationRoute.NickNameSettingGraph.route,
    ) {
        composable(
            route = "${NavigationRoute.NickNameSettingGraph.NickNameSettingScreen.route}/{startRoute}",
            arguments = listOf(
                navArgument("startRoute") { type = NavType.StringType },
            ),
        ) { navBackStackEntry ->
            val nickNameSettingComponent = componentProvider<NickNameSettingComponentProvider>().provideNickNameSettingComponent()
            val nickNameViewModel = daggerViewModel {
                nickNameSettingComponent.getViewModel()
            }
            val startRoute = navBackStackEntry.arguments?.getString("startRoute") ?: ""
            NickNameSettingRoute(nickNameViewModel, startRoute) { route ->
                when (route) {
                    NavigationRoute.HomeGraph.HomeScreen.route -> {
                        navController.navigateToHome(
                            navOptions = navOptions {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            },
                        )
                    }
                    NavigationRoute.InvitationGraph.SendInvitationScreen.route -> {
                        navController.navigateToInvitation(
                            navOptions = navOptions {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            },
                        )
                    }
                    NavigationRoute.UserGraph.UserScreen.route -> {
                        navController.navigateToUser(
                            action = "success",
                            navOptions = navOptions {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            },
                        )
                    }
                    else -> {
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}
