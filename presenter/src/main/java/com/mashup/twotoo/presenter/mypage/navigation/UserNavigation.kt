package com.mashup.twotoo.presenter.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.guid.navigation.navigateToGuide
import com.mashup.twotoo.presenter.mypage.MyPageRoute
import com.mashup.twotoo.presenter.mypage.di.UserComponentProvider
import com.mashup.twotoo.presenter.mypage.model.MyPageItem
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.nickname.navigation.navigateToOnNickNameSetting
import com.mashup.twotoo.presenter.onboarding.navigation.navigateToOnBoarding
import com.mashup.twotoo.presenter.util.componentProvider

fun NavController.navigateToUser(action: String = "mypage", navOptions: NavOptions? = null) {
    this.navigate(route = "${NavigationRoute.UserGraph.UserScreen.route}/$action", navOptions = navOptions)
}

fun NavGraphBuilder.userGraph(navController: NavController) {
    navigation(startDestination = "${NavigationRoute.UserGraph.route}/{action}", route = NavigationRoute.UserGraph.route) {
        composable(route = "${NavigationRoute.UserGraph.UserScreen.route}/{action}") { navBackStackEntry ->
            val userComponent = componentProvider<UserComponentProvider>().provideUserComponent()
            val userViewModel = daggerViewModel {
                userComponent.getViewModel()
            }
            val action = navBackStackEntry.arguments?.getString("action") ?: ""
            val isChangeNicknameSuccess = action == "success"

            MyPageRoute(
                isChangeNicknameSuccess,
                userViewModel = userViewModel,
                navigateToRoute = { route ->
                    when (route) {
                        MyPageItem.SignOut.route -> {
                            navController.navigateToOnBoarding(
                                navOptions = navOptions {
                                    popUpTo(navController.graph.id) {
                                        inclusive = true
                                    }
                                },
                            )
                        }
                        MyPageItem.DeletePartner.route -> {
                            navController.navigateToOnNickNameSetting(
                                navOptions = navOptions {
                                    popUpTo(navController.graph.id) {
                                        inclusive = true
                                    }
                                },
                            )
                        }
                        NavigationRoute.NickNameSettingGraph.route -> {
                            navController.navigateToOnNickNameSetting("mypage")
                        }
                        else -> { navController.navigateToGuide(route) } }
                },
            )
        }
    }
}
