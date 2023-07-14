package com.mashup.twotoo.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.mashup.twotoo.presenter.createChallenge.navigation.createChallengeGraph
import com.mashup.twotoo.presenter.garden.navigation.gardenGraph
import com.mashup.twotoo.presenter.home.navigation.homeGraph
import com.mashup.twotoo.presenter.invite.navigation.invitationGraph
import com.mashup.twotoo.presenter.mypage.navigation.userGraph
import com.mashup.twotoo.presenter.nickname.navigation.nickNameSettingGraph
import com.mashup.twotoo.presenter.onboarding.navigation.onBoardingGraph
import com.mashup.twotoo.presenter.ui.TwoTooAppState

@Composable
fun TwoTooNavHost(
    appState: TwoTooAppState,
    modifier: Modifier = Modifier,
    startDestination: String = NavigationRoute.OnBoardingGraph.OnboardingScreen.route
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        gardenGraph(navController = navController)
        homeGraph(navController = navController)
        userGraph()
        onBoardingGraph(navController = navController)
        nickNameSettingGraph(navController = navController)
        invitationGraph(navController = navController)
        createChallengeGraph(navController = navController)
    }
}
