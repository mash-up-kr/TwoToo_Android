package com.mashup.twotoo.presenter.garden.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.garden.GardenRoute
import com.mashup.twotoo.presenter.garden.di.GardenComponentProvider
import com.mashup.twotoo.presenter.guid.navigation.navigateToGuide
import com.mashup.twotoo.presenter.history.navigation.navigateToHistory
import com.mashup.twotoo.presenter.mypage.model.GuideUrlItem
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.util.componentProvider

fun NavController.navigateToGarden(isComplete: Boolean = false, navOptions: NavOptions? = null) {
    this.navigate(route = "${NavigationRoute.GardenGraph.GardenScreen.route}/$isComplete", navOptions = navOptions)
}

fun NavGraphBuilder.gardenGraph(navController: NavController) {
    navigation(startDestination = "${NavigationRoute.GardenGraph.GardenScreen.route}/{isComplete}", route = NavigationRoute.GardenGraph.route) {
        composable(
            route = "${NavigationRoute.GardenGraph.GardenScreen.route}/{isComplete}",
            arguments = listOf(
                navArgument("isComplete") { type = NavType.BoolType },
            ),
        ) {
                navBackStackEntry ->
            val isComplete = navBackStackEntry.arguments?.getBoolean("isComplete") ?: false
            val gardenComponent = componentProvider<GardenComponentProvider>().provideGardenComponent()
            val gardenViewModel = daggerViewModel {
                gardenComponent.getViewModel()
            }

            GardenRoute(
                isComplete = isComplete,
                navigateToGarden = { challengeNo -> navController.navigateToHistory(challengeNo = challengeNo) },
                navigateToGuide = { navController.navigateToGuide(GuideUrlItem.UsingGuide.name) },
                gardenViewModel = gardenViewModel,
            )
        }
    }
}
