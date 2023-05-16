package com.mashup.twotoo.presenter.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.mashup.twotoo.presenter.garden.navigation.gardenNavigationRoute
import com.mashup.twotoo.presenter.garden.navigation.navigateToGarden
import com.mashup.twotoo.presenter.home.navigation.homeNavigationRoute
import com.mashup.twotoo.presenter.home.navigation.navigateToHome
import com.mashup.twotoo.presenter.navigation.TopLevelDestination
import com.mashup.twotoo.presenter.navigation.TopLevelDestination.Garden
import com.mashup.twotoo.presenter.navigation.TopLevelDestination.Home
import com.mashup.twotoo.presenter.navigation.TopLevelDestination.User
import com.mashup.twotoo.presenter.user.navigation.navigateToUser
import com.mashup.twotoo.presenter.user.navigation.userNavigationRoute

/**
 * @Created by 김현국 2023/05/16
 * @Time 3:35 PM
 */
@Composable
fun rememberTwoTooAppState(
    navController: NavHostController = rememberNavController(),
): TwoTooAppState {
    return remember(
        navController,
    ) {
        TwoTooAppState(
            navController = navController,
        )
    }
}

@Stable
class TwoTooAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination
    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            gardenNavigationRoute -> Garden
            homeNavigationRoute -> Home
            userNavigationRoute -> User
            else -> null
        }
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    fun navigationToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        when (topLevelDestination) {
            Garden -> navController.navigateToGarden(navOptions = topLevelOptions)
            Home -> navController.navigateToHome(navOptions = topLevelOptions)
            User -> navController.navigateToUser(navOptions = topLevelOptions)
        }
    }
}
