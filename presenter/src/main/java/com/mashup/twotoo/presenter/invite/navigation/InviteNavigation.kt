package com.mashup.twotoo.presenter.invite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.invite.SendInvitationRoute
import com.mashup.twotoo.presenter.invite.WaitingAcceptPairRoute
import com.mashup.twotoo.presenter.invite.di.InviteComponentProvider
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.util.componentProvider

fun NavController.navigateToInvitation(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.InvitationGraph.route, navOptions = navOptions)
}

private fun NavController.navigateToWaitingAcceptPair(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.InvitationGraph.route, navOptions = navOptions)
}
fun NavGraphBuilder.invitationGraph(
    navController: NavController
) {
    navigation(
        startDestination = NavigationRoute.InvitationGraph.SendInvitationScreen.route,
        route = NavigationRoute.InvitationGraph.route,
    ) {
        composable(
            route = NavigationRoute.InvitationGraph.SendInvitationScreen.route,
        ) {
            val inviteComponent = componentProvider<InviteComponentProvider>().provideInviteComponent()
            val inviteViewModel = daggerViewModel {
                inviteComponent.getViewModel()
            }
            SendInvitationRoute(inviteViewModel) { navController.navigateToWaitingAcceptPair() }
        }
        composable(route = NavigationRoute.InvitationGraph.WaitingAcceptPairScreen.route) {
            val inviteComponent = componentProvider<InviteComponentProvider>().provideInviteComponent()
            val inviteViewModel = daggerViewModel {
                inviteComponent.getViewModel()
            }
            WaitingAcceptPairRoute(inviteViewModel)
        }
    }
}
