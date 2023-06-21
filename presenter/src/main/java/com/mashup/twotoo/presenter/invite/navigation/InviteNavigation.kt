package com.mashup.twotoo.presenter.invite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.invite.SendInvitationRoute
import com.mashup.twotoo.presenter.invite.WaitingAcceptPairRoute

sealed class InvitationNavigation(val route: String) {
    object SendInvitation : InvitationNavigation("invitation_route")
    object WaitingAcceptPair : InvitationNavigation("waiting_accept_route")
}

fun NavController.navigateToInvitation(navOptions: NavOptions? = null) {
    this.navigate(route = InvitationNavigation.SendInvitation.route, navOptions = navOptions)
}
fun NavGraphBuilder.invitationGraph(
    navController: NavController
) {
    navigation(
        startDestination = InvitationNavigation.SendInvitation.route,
        route = "SendInvitation",
    ) {
        composable(route = InvitationNavigation.SendInvitation.route) {
            SendInvitationRoute { navController.navigate(InvitationNavigation.WaitingAcceptPair.route) }
        }
        composable(route = InvitationNavigation.WaitingAcceptPair.route) {
            WaitingAcceptPairRoute()
        }
    }
}
