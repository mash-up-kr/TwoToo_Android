package com.mashup.twotoo.presenter.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
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

    fun navigationToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelOptions = navOptions {
            popUpTo(navController.graph.id) {
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
        Log.i("hyejin", "currentDestination: ${currentDestination?.route}  ${currentDestination?.parent?.route}")
        // currentDestination 이 garden/screen/{isComplete}  이렇게 넘오게 되어서 parent 보도록 수정함, 그러면 home, garden, user 이렇게 확인함
        return when (currentDestination?.parent?.route) {
            NavigationRoute.HomeGraph.route,
            NavigationRoute.GardenGraph.route,
            NavigationRoute.UserGraph.route,
            -> true
            else -> {
                false
            }
        }
    }
}
