package com.mashup.twotoo.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.mashup.twotoo.presenter.garden.navigation.gardenGraph
import com.mashup.twotoo.presenter.home.navigation.homeGraph
import com.mashup.twotoo.presenter.invite.navigation.invitationGraph
import com.mashup.twotoo.presenter.invite.navigation.navigateToInvitation
import com.mashup.twotoo.presenter.onboarding.navigation.OnBoardingRoute
import com.mashup.twotoo.presenter.onboarding.navigation.navigateToOnNickNameSetting
import com.mashup.twotoo.presenter.onboarding.navigation.onBoardingGraph
import com.mashup.twotoo.presenter.onboarding.navigation.onNickNameSettingGraph
import com.mashup.twotoo.presenter.ui.TwoTooAppState
import com.mashup.twotoo.presenter.user.navigation.userGraph

@Composable
fun TwoTooNavHost(
    appState: TwoTooAppState,
    modifier: Modifier = Modifier,
    startDestination: String = OnBoardingRoute,
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
        onBoardingGraph {
            navController.navigateToOnNickNameSetting()
        }
        onNickNameSettingGraph {
            navController.navigateToInvitation()
        }
        invitationGraph(navController)
    }
}
