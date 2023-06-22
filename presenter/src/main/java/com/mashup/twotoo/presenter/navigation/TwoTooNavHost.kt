package com.mashup.twotoo.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.mashup.twotoo.presenter.garden.navigation.gardenGraph
import com.mashup.twotoo.presenter.home.navigation.HomeNavigationRoute
import com.mashup.twotoo.presenter.home.navigation.homeGraph
import com.mashup.twotoo.presenter.onboarding.navigation.onBoardingGraph
import com.mashup.twotoo.presenter.ui.TwoTooAppState
import com.mashup.twotoo.presenter.mypage.navigation.userGraph

@Composable
fun TwoTooNavHost(
    appState: TwoTooAppState,
    modifier: Modifier = Modifier,
    startDestination: String = HomeNavigationRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        gardenGraph()
        homeGraph()
        userGraph()
        onBoardingGraph()
    }
}
