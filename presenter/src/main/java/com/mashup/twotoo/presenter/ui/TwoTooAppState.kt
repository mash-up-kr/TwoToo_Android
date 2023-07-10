package com.mashup.twotoo.presenter.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.garden.navigation.navigateToGarden
import com.mashup.twotoo.presenter.home.navigation.navigateToHome
import com.mashup.twotoo.presenter.mypage.navigation.navigateToUser
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.navigation.TopLevelDestination
import com.mashup.twotoo.presenter.navigation.TopLevelDestination.Garden
import com.mashup.twotoo.presenter.navigation.TopLevelDestination.Home
import com.mashup.twotoo.presenter.navigation.TopLevelDestination.User

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
        @Composable get() = currentDestination?.route?.let { TopLevelDestination.findBy(it) }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    val getContainerColorByDestination: Color
        @Composable get() = if (currentTopLevelDestination == Home) {
            TwoTooTheme.color.mainPink
        } else {
            TwoTooTheme.color.backgroundYellow
        }

    val getUnSelectedColorByDestination: Color
        @Composable get() = if (currentTopLevelDestination == Home) {
            TwoTooTheme.color.backgroundYellow
        } else {
            TwoTooTheme.color.mainPink
        }
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

    @Composable
    fun isBottomBarVisible(): Boolean {
        return when (currentDestination?.route) {
            NavigationRoute.HomeGraph.HomeScreen.route,
            NavigationRoute.GardenGraph.GardenScreen.route,
            NavigationRoute.UserGraph.UserScreen.route,
            -> true
            NavigationRoute.HistoryGraph.HistoryScreen.route -> false
            else -> {
                false
            }
        }
    }
}
