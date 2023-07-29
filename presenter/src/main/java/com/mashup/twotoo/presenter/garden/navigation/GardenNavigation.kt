package com.mashup.twotoo.presenter.garden.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.garden.GardenRoute
import com.mashup.twotoo.presenter.garden.di.GardenComponentProvider
import com.mashup.twotoo.presenter.guid.navigation.navigateToGuide
import com.mashup.twotoo.presenter.history.navigation.navigateToHistory
import com.mashup.twotoo.presenter.mypage.model.GuideUrlItem
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.util.componentProvider

fun NavController.navigateToGarden(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.GardenGraph.route, navOptions = navOptions)
}

fun NavGraphBuilder.gardenGraph(navController: NavController) {
    navigation(startDestination = NavigationRoute.GardenGraph.GardenScreen.route, route = NavigationRoute.GardenGraph.route) {
        composable(route = NavigationRoute.GardenGraph.GardenScreen.route) {
            val gardenComponent = componentProvider<GardenComponentProvider>().provideGardenComponent()
            val gardenViewModel = daggerViewModel {
                gardenComponent.getViewModel()
            }

            GardenRoute(
                navigateToGarden = { challengeNo -> navController.navigateToHistory(challengeNo = challengeNo) },
                navigateToGuide = { navController.navigateToGuide(GuideUrlItem.UsingGuide.name) },
                gardenViewModel = gardenViewModel,
            )
        }
    }
}
