package com.mashup.twotoo.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.mashup.twotoo.presenter.garden.navigation.gardenRoute
import com.mashup.twotoo.presenter.home.navigation.homeNavigationRoute
import com.mashup.twotoo.presenter.home.navigation.homeRoute
import com.mashup.twotoo.presenter.onboarding.navigation.onBoardingRoute
import com.mashup.twotoo.presenter.ui.TwoTooAppState
import com.mashup.twotoo.presenter.user.navigation.userRoute

@Composable
fun TwoTooNavHost(
    appState: TwoTooAppState,
    modifier: Modifier = Modifier,
    startDestination: String = homeNavigationRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        gardenRoute()
        homeRoute()
        userRoute()
        onBoardingRoute()
    }
}
